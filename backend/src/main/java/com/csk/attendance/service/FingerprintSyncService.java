package com.csk.attendance.service;

import java.time.ZonedDateTime;

public interface FingerprintSyncService {
    void performScheduledSync();
    void manualSync(ZonedDateTime from, ZonedDateTime to);
}
