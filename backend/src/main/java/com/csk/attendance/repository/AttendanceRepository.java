package com.csk.attendance.repository;

import com.csk.attendance.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.*;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    List<Attendance> findByUserIdAndDate(UUID userId, LocalDate date);
}

