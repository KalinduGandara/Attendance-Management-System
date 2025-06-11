package com.csk.attendance.repository;

import com.csk.attendance.entity.SyncLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SyncLogRepository extends JpaRepository<SyncLog, UUID> {}
