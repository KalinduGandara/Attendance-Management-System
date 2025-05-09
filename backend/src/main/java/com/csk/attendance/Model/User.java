package com.csk.attendance.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Field name is required.")
    private String name;

    @NotEmpty(message = "Field password is required.")
    private String password;

    @NotEmpty(message = "Field role is required.")
    private String role;

}
