package com.csk.attendance.service;

import com.csk.attendance.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(UUID id);
    User createUser(User user);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
}
