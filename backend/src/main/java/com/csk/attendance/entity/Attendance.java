package com.csk.attendance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PRESENT, ABSENT, MISSING, LATE
    }
}