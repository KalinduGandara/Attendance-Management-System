package com.csk.attendance.repository;

import com.csk.attendance.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShiftRepository extends JpaRepository<Shift, UUID> {}
