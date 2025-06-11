package com.csk.attendance.controller;

import com.csk.attendance.dto.ManualSyncRequest;
import com.csk.attendance.service.FingerprintSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class SyncController {

    private final FingerprintSyncService syncService;

    @PostMapping("/manual")
    public ResponseEntity<String> manualSync(@RequestBody ManualSyncRequest request) {
        syncService.manualSync(request.getFrom(), request.getTo());
        return ResponseEntity.ok("Manual sync triggered from " + request.getFrom() + " to " + request.getTo());
    }

    @PostMapping("/auto")
    public ResponseEntity<String> triggerAutoSync() {
        syncService.performScheduledSync();
        return ResponseEntity.ok("Scheduled sync triggered");
    }
}
