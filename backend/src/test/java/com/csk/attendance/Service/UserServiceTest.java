package com.csk.attendance.Service;

import com.csk.attendance.Model.User;
import com.csk.attendance.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setId(1L);
        user1.setName("Test User 1");
        user1.setPassword("pass1");
        user1.setRole("USER");

        user2 = new User();
        user2.setId(2L);
        user2.setName("Test User 2");
        user2.setPassword("pass2");
        user2.setRole("ADMIN");
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        List<User> expectedUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getAllUsers();

        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers.get(0).getName(), actualUsers.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        User actualUser = userService.getUserById(1L);

        assertNotNull(actualUser);
        assertEquals(user1.getName(), actualUser.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_shouldReturnNull_whenUserDoesNotExist() {
        when(userRepository.findById(3L)).thenReturn(Optional.empty());

        User actualUser = userService.getUserById(3L);

        assertNull(actualUser);
        verify(userRepository, times(1)).findById(3L);
    }

    @Test
    void createUser_shouldSaveAndReturnUser() {
        User newUser = new User();
        newUser.setName("New User");
        newUser.setPassword("newpass");
        newUser.setRole("USER");

        User savedUser = new User();
        savedUser.setId(3L);
        savedUser.setName("New User");
        savedUser.setPassword("newpass");
        savedUser.setRole("USER");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User actualUser = userService.createUser(newUser);

        assertNotNull(actualUser);
        assertEquals(savedUser.getId(), actualUser.getId());
        assertEquals(newUser.getName(), actualUser.getName());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void updateUser_shouldUpdateAndReturnUser_whenUserExists() {
        User userDetailsToUpdate = new User();
        userDetailsToUpdate.setName("Updated User 1");
        userDetailsToUpdate.setRole("ADMIN");
        userDetailsToUpdate.setPassword("updatedpass1");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("Updated User 1");
        updatedUser.setRole("ADMIN");
        updatedUser.setPassword("updatedpass1");


        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User actualUser = userService.updateUser(1L, userDetailsToUpdate);

        assertNotNull(actualUser);
        assertEquals(1L, actualUser.getId());
        assertEquals("Updated User 1", actualUser.getName());
        assertEquals("ADMIN", actualUser.getRole());
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).save(userDetailsToUpdate);
        // Check that setId was called on userDetailsToUpdate
        assertEquals(1L, userDetailsToUpdate.getId());
    }

    @Test
    void updateUser_shouldReturnNull_whenUserDoesNotExist() {
        User userDetailsToUpdate = new User();
        userDetailsToUpdate.setName("Non Existent User");

        when(userRepository.existsById(3L)).thenReturn(false);

        User actualUser = userService.updateUser(3L, userDetailsToUpdate);

        assertNull(actualUser);
        verify(userRepository, times(1)).existsById(3L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUser_shouldCallDeleteById() {
        long userIdToDelete = 1L;
        // Mock the deleteById method to do nothing, as it's void
        doNothing().when(userRepository).deleteById(userIdToDelete);

        userService.deleteUser(userIdToDelete);

        // Verify that deleteById was called exactly once with the correct ID
        verify(userRepository, times(1)).deleteById(userIdToDelete);
    }

    @Test
    void deleteUser_shouldCallDeleteById_evenWhenUserDoesNotExist() {
        long userIdToDelete = 99L; // Non-existent user
        // Mock the deleteById method to do nothing, as it's void
        // Spring Data JPA's deleteById typically doesn't throw an exception if the entity is not found.
        doNothing().when(userRepository).deleteById(userIdToDelete);

        userService.deleteUser(userIdToDelete);

        // Verify that deleteById was called exactly once with the correct ID
        verify(userRepository, times(1)).deleteById(userIdToDelete);
    }
}
