package com.csk.attendance.service.Impl;

import com.csk.attendance.dto.*;
import com.csk.attendance.entity.User;
import com.csk.attendance.repository.UserRepository;
import com.csk.attendance.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByName(request.getName())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return new AuthResponse("SESSION", user.getRole().name(), user.getName());
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .name(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.valueOf(request.getRole().toUpperCase()))
                .department(request.getDepartment())
                .isActive(true)
                .build();
        userRepository.save(user);
        return new AuthResponse("SESSION", user.getRole().name(), user.getName());
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        // For demo, fetch first user only (real implementation should use session context)
        User user = userRepository.findAll().getFirst();
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}