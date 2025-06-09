package com.csk.attendance.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String department;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Shift shift;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> customFields = new HashMap<>();

    private Boolean isActive;

    public enum Role {
        ADMIN, EMPLOYEE
    }
}
