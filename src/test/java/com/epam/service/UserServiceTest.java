package com.epam.service;

import com.epam.domain.User;
import com.epam.dto.request.ChangeLogin;
import com.epam.repository.UserRepository;
import com.epam.utils.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Given
        User user = new User("John", "Doe", null, null, true);

        when(userRepository.save(any())).thenReturn(user);

        // When
        User savedUser = userService.save(user);

        // Then
        assertNotNull(savedUser);
        assertEquals("john.doe", savedUser.getUsername());
        assertNotNull(savedUser.getPassword());

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testFindByUsernameWhenUserExists() {
        // Given
        String username = "john.doe";
        User user = new User("John", "Doe", username, "password", true);

        when(userRepository.findByUsername(username)).thenReturn(user);

        // When
        User foundUser = userService.findByUsername(username);

        // Then
        assertNotNull(foundUser);
        assertEquals(user, foundUser);

        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByUsernameWhenUserDoesNotExist() {
        // Given
        String nonExistingUsername = "nonExistingUser";

        when(userRepository.findByUsername(nonExistingUsername)).thenReturn(null);

        // When, Then
        assertThrows(UserNotFoundException.class, () -> userService.findByUsername(nonExistingUsername));

        verify(userRepository, times(1)).findByUsername(nonExistingUsername);
    }

    @Test
    void testUpdateUser() {
        // Given
        User user = new User("John", "Doe", "john.doe", "password", true);

        when(userRepository.update(any())).thenReturn(user);

        // When
        User updatedUser = userService.update(user);

        // Then
        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);

        verify(userRepository, times(1)).update(any());
    }

    @Test
    void testUpdatePasswordWithCorrectOldPassword() {
        // Given
        String username = "john.doe";
        User user = new User("John", "Doe", username, "oldPassword", true);
        ChangeLogin request = new ChangeLogin(username, "oldPassword", "newPassword");

        when(userRepository.findByUsername(username)).thenReturn(user);

        // When
        userService.updatePassword(request);

        // Then: No exception should be thrown

        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).updatePassword(user);
    }

    @Test
    void testUpdatePasswordWithIncorrectOldPassword() {
        // Given
        String username = "john.doe";
        User user = new User("John", "Doe", username, "oldPassword", true);
        ChangeLogin request = new ChangeLogin(username, "incorrectPassword", "newPassword");

        when(userRepository.findByUsername(username)).thenReturn(user);

        // When, Then
        assertThrows(RuntimeException.class, () -> userService.updatePassword(request));

        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, never()).updatePassword(any());
    }

    @Test
    void testUpdatePasswordWhenUserNotFound() {
        // Given
        String nonExistingUsername = "nonExistingUser";
        ChangeLogin request = new ChangeLogin(nonExistingUsername, "oldPassword", "newPassword");

        when(userRepository.findByUsername(nonExistingUsername)).thenReturn(null);

        // When, Then
        assertThrows(UserNotFoundException.class, () -> userService.updatePassword(request));

        verify(userRepository, times(1)).findByUsername(nonExistingUsername);
        verify(userRepository, never()).updatePassword(any());
    }

    @Test
    void testExistsByUsername() {
        // Given
        String existingUsername = "existingUser";

        when(userRepository.existsByUsername(existingUsername)).thenReturn(true);

        // When
        boolean exists = userService.existsByUsername(existingUsername);

        // Then
        assertTrue(exists);

        verify(userRepository, times(1)).existsByUsername(existingUsername);
    }

    @Test
    void testUsernameGenerator() {
        // Given
        String firstName = "John";
        String lastName = "Doe";

        when(userRepository.existsByUsername(anyString())).thenReturn(false);

        // When
        String generatedUsername = userService.usernameGenerator(firstName, lastName);

        // Then
        assertEquals("john.doe", generatedUsername);

        verify(userRepository, times(1)).existsByUsername("john.doe");
    }

    @Test
    void testPasswordGenerator() {
        // When
        String generatedPassword = userService.passwordGenerator();

        // Then
        assertNotNull(generatedPassword);
        assertEquals(10, generatedPassword.length());
    }

    @Test
    void testUsernameGeneratorWithoutCollision() {
        // Given
        String firstName = "John";
        String lastName = "Doe";

        when(userRepository.existsByUsername(anyString())).thenReturn(false);

        // When
        String generatedUsername = userService.usernameGenerator(firstName, lastName);

        // Then
        String expectedUsername = firstName.toLowerCase() + "." + lastName.toLowerCase();
        assertEquals(expectedUsername, generatedUsername);

        // Verify that existsByUsername was called once with the expected parameter
        verify(userRepository, times(1)).existsByUsername(expectedUsername);
    }

    @Test
    void testUsernameGeneratorWithCollision() {
        // Given
        String firstName = "John";
        String lastName = "Doe";

        when(userRepository.existsByUsername(anyString())).thenReturn(true, true, false);

        // When
        String generatedUsername = userService.usernameGenerator(firstName, lastName);

        // Then
        String expectedUsername = firstName.toLowerCase() + "." + lastName.toLowerCase() + "2";
        assertEquals(expectedUsername, generatedUsername);

        // Verify that existsByUsername was called three times with the expected parameters
        verify(userRepository, times(1)).existsByUsername(expectedUsername);
    }
}

