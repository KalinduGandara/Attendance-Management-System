package com.csk.attendance.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String department;
    private String role;
    private UUID shiftId;
}