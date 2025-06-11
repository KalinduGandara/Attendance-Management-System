package com.csk.attendance.service.Impl;


import com.csk.attendance.dto.FingerprintApiRequest;
import com.csk.attendance.entity.*;
import com.csk.attendance.repository.*;
import com.csk.attendance.service.FingerprintSyncService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FingerprintSyncServiceImpl implements FingerprintSyncService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final SyncLogRepository syncLogRepository;
    private final RestTemplate restTemplate;
//    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${sync.retry.max:3}")
    private int maxRetryAttempts;

//    @Value("${fingerprint.api.base.url}")
    private final String FINGERPRINT_API_BASE_URL = "https://localhost";
    private final String LOGIN_ID = "admin";
    private final String LOGIN_PASSWORD = "";

    private final String FINGERPRINT_SYNC_URL = FINGERPRINT_API_BASE_URL + "/api/events";
    private final String LOGIN_URL = FINGERPRINT_API_BASE_URL + "/api/login";

    private String sessionId;
    @Override
    public void performScheduledSync() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime startOfYesterday = now.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        ZonedDateTime endOfYesterday = now.minusDays(1).withHour(23).withMinute(59).withSecond(59);
        syncWithRetry(startOfYesterday, endOfYesterday);
    }

    @Override
    public void manualSync(ZonedDateTime from, ZonedDateTime to) {
        syncWithRetry(from, to);
    }

    private void login() {
        Map<String, Object> body = Map.of(
                "User", Map.of("login_id", LOGIN_ID, "password", LOGIN_PASSWORD)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(LOGIN_URL, request, String.class);
        sessionId = response.getHeaders().getFirst("bs-session-id");

        if (sessionId == null) {
            throw new IllegalStateException("Login failed: bs-session-id header missing");
        }
    }

    private void syncWithRetry(ZonedDateTime from, ZonedDateTime to) {
        int attempts = 0;
        while (attempts < maxRetryAttempts) {
            try {
                if (sessionId == null) login();
                sync(from, to);
                return; // success
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED || e.getStatusCode() == HttpStatus.FORBIDDEN) {
                    sessionId = null; // force re-login
                    attempts++;
                    log.warn("Session expired or unauthorized. Retrying login... (attempt {} of {})", attempts, maxRetryAttempts);
                } else {
                    throw e;
                }
            } catch (Exception e) {
                log.error("Sync failed on attempt {}: {}", attempts + 1, e.getMessage());
                break;
            }
        }
        throw new RuntimeException("Sync failed after " + maxRetryAttempts + " attempts");
    }

    private void sync(ZonedDateTime from, ZonedDateTime to) throws Exception {
        FingerprintApiRequest requestPayload = new FingerprintApiRequest();
        FingerprintApiRequest.Query query = new FingerprintApiRequest.Query();
        query.setLimit(100);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        String fromFormatted = from.format(formatter);
        String toFormatted = to.format(formatter);

        FingerprintApiRequest.Condition dateCondition = new FingerprintApiRequest.Condition();
        dateCondition.setColumn("datetime");
        dateCondition.setOperator(3);
        dateCondition.setValues(List.of(fromFormatted, toFormatted));

        FingerprintApiRequest.Condition tnaCondition = new FingerprintApiRequest.Condition();
        tnaCondition.setColumn("tna_key");
        tnaCondition.setOperator(2);
        tnaCondition.setValues(List.of("1", "2"));

        FingerprintApiRequest.Order order = new FingerprintApiRequest.Order();
        order.setColumn("datetime");
        order.setDescending(true);

        query.setConditions(List.of(dateCondition, tnaCondition));
        query.setOrders(List.of(order));
        requestPayload.setQuery(query);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("bs-session-id", sessionId);
        HttpEntity<FingerprintApiRequest> requestEntity = new HttpEntity<>(requestPayload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(FINGERPRINT_SYNC_URL, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode rows = root.path("EventCollection").path("rows");
            for (JsonNode row : rows) {
                String userId = row.path("user_id").path("user_id").asText();
                String dateTime = row.path("datetime").asText();
                Optional<User> userOpt = userRepository.findAll().stream()
                        .filter(u -> u.getEmail().contains(userId)) // Simulated user match
                        .findFirst();

                if (userOpt.isPresent()) {
                    LocalDateTime dt = LocalDateTime.parse(dateTime.replace("Z", ""));
                    Attendance att = Attendance.builder()
                            .id(UUID.randomUUID())
                            .user(userOpt.get())
                            .date(dt.toLocalDate())
                            .timeIn(dt.toLocalTime())
                            .status(Attendance.Status.PRESENT)
                            .build();
                    attendanceRepository.save(att);
                }
            }

            syncLogRepository.save(SyncLog.builder()
                    .id(UUID.randomUUID())
                    .timestamp(LocalDateTime.now())
                    .status(SyncLog.Status.SUCCESS)
                    .message("Synced successfully")
                    .build());
        }
    }
}
