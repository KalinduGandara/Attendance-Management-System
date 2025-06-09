package com.csk.attendance.util;

import com.csk.attendance.entity.User;
import com.csk.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.findByName("admin").orElseGet(() -> {
            User admin = new User();
            admin.setId(UUID.randomUUID());
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("secret123"));
            admin.setRole(User.Role.ADMIN);
            return userRepository.save(admin);
        });
    }
}

