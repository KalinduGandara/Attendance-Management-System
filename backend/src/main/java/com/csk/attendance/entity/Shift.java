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
public class Shift {
    @Id
    private UUID id;

    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
}
