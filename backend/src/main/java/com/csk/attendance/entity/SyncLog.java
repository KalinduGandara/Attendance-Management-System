package com.csk.attendance.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyncLog {
    @Id
    private UUID id;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String message;

    public enum Status {
        SUCCESS, FAILURE
    }
}
