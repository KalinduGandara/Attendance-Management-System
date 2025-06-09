package com.csk.attendance.service;

import com.csk.attendance.dto.AuthResponse;
import com.csk.attendance.dto.ChangePasswordRequest;
import com.csk.attendance.dto.LoginRequest;
import com.csk.attendance.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    void changePassword(ChangePasswordRequest request);
}
