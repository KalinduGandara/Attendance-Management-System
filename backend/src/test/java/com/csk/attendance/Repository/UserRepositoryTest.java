package com.csk.attendance.Repository;

import com.csk.attendance.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException; // For delete test if needed
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
// @ActiveProfiles("test") // If you have a specific test profile e.g. application-test.properties
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll(); // Clean up before each test

        user1 = new User();
        user1.setName("Test User 1");
        user1.setPassword("pass1");
        user1.setRole("USER");

        user2 = new User();
        user2.setName("Test User 2");
        user2.setPassword("pass2");
        user2.setRole("ADMIN");
    }

    @Test
    void save_shouldPersistUserAndAssignId() {
        User savedUser = userRepository.save(user1);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull().isGreaterThan(0L);
        assertThat(savedUser.getName()).isEqualTo("Test User 1");

        User retrievedUser = entityManager.find(User.class, savedUser.getId());
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getName()).isEqualTo("Test User 1");
    }

    @Test
    void findById_shouldReturnUser_whenUserExists() {
        User persistedUser1 = entityManager.persistAndFlush(user1);

        Optional<User> foundUserOpt = userRepository.findById(persistedUser1.getId());

        assertThat(foundUserOpt).isPresent();
        assertThat(foundUserOpt.get().getName()).isEqualTo(persistedUser1.getName());
    }

    @Test
    void findById_shouldReturnEmpty_whenUserDoesNotExist() {
        Optional<User> foundUserOpt = userRepository.findById(999L); // Non-existent ID
        assertThat(foundUserOpt).isNotPresent();
    }

    @Test
    void findAll_shouldReturnAllUsers() {
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        List<User> users = userRepository.findAll();

        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);
        assertThat(users).extracting(User::getName).containsExactlyInAnyOrder("Test User 1", "Test User 2");
    }

    @Test
    void findAll_shouldReturnEmptyList_whenNoUsers() {
        List<User> users = userRepository.findAll();
        assertThat(users).isNotNull();
        assertThat(users).isEmpty();
    }


    @Test
    void deleteById_shouldRemoveUser() {
        User persistedUser1 = entityManager.persistAndFlush(user1);
        Long userId = persistedUser1.getId();

        userRepository.deleteById(userId);
        // entityManager.flush(); // Ensure delete is processed if not automatically

        User deletedUser = entityManager.find(User.class, userId);
        assertThat(deletedUser).isNull();

        Optional<User> foundAfterDelete = userRepository.findById(userId);
        assertThat(foundAfterDelete).isNotPresent();
    }

    @Test
    void deleteById_shouldDoNothing_whenUserDoesNotExist() {
        // Attempt to delete a non-existent user
        // JpaRepository's deleteById doesn't throw an exception if the entity is not found.
        // So, we just verify that no exception is thrown.
        long nonExistentId = 999L;
        userRepository.deleteById(nonExistentId);
        // No assertion needed other than that no exception was thrown.
        // Optionally, verify count if possible or that other records are unaffected.
        assertThat(userRepository.count()).isEqualTo(0); // Assuming DB was empty before this test
    }


}
