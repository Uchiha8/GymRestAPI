package com.epam.service;

import com.epam.domain.Trainee;
import com.epam.domain.User;
import com.epam.dto.request.StatusRequest;
import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.request.UpdateTraineeRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.dto.response.TraineeProfileResponse;
import com.epam.dto.response.UpdateTraineeResponse;
import com.epam.repository.TraineeRepository;
import com.epam.utils.exception.TraineeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TraineeServiceTest {

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTrainee() {
        // Given
        TraineeRegistrationRequest request = new TraineeRegistrationRequest("John", "Doe", new Date(), "Address");

        User savedUser = new User("John", "Doe", "john.doe", "password", false);
        when(userService.save(any())).thenReturn(savedUser);

        Trainee savedTrainee = new Trainee(new Date(), "Address", savedUser, List.of());
        when(traineeRepository.save(any())).thenReturn(savedTrainee);

        // When
        RegistrationResponse response = traineeService.save(request);

        // Then
        assertNotNull(response);
        assertEquals("john.doe", response.username());
        assertNotNull(response.password());

        verify(userService, times(1)).save(any());
        verify(traineeRepository, times(1)).save(any());
    }

    @Test
    void testFindByUsernameWhenTraineeExists() {
        // Given
        String username = "john.doe";
        Trainee trainee = new Trainee(new Date(), "Address", new User("John", "Doe", username, "password", true), List.of());

        when(traineeRepository.existsByUsername(username)).thenReturn(true);
        when(traineeRepository.findByUsername(username)).thenReturn(trainee);

        // When
        TraineeProfileResponse profileResponse = traineeService.findByUsername(username);

        // Then
        assertNotNull(profileResponse);
        assertEquals("John", profileResponse.firstName());
        assertEquals("Doe", profileResponse.lastName());

        verify(traineeRepository, times(1)).existsByUsername(username);
        verify(traineeRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByUsernameWhenTraineeDoesNotExist() {
        // Given
        String nonExistingUsername = "nonExistingUser";

        when(traineeRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(RuntimeException.class, () -> traineeService.findByUsername(nonExistingUsername));

        verify(traineeRepository, times(1)).existsByUsername(nonExistingUsername);
        verify(traineeRepository, never()).findByUsername(any());
    }

    @Test
    void testUpdateTrainee() {
        // Given
        String username = "john.doe";
        UpdateTraineeRequest request = new UpdateTraineeRequest(username, "John", "Doe", new Date(), "New Address", true);

        when(traineeRepository.existsByUsername(username)).thenReturn(true);

        User existingUser = new User("John", "Doe", username, "oldPassword", true);
        when(userService.findByUsername(username)).thenReturn(existingUser);

        Trainee existingTrainee = new Trainee(new Date(), "Old Address", existingUser, List.of());
        when(traineeRepository.findByUsername(username)).thenReturn(existingTrainee);

        User updatedUser = new User("John", "Doe", username, "oldPassword", true);
        when(userService.update(any())).thenReturn(updatedUser);

        Trainee updatedTrainee = new Trainee(new Date(), "New Address", updatedUser, List.of());
        when(traineeRepository.update(any())).thenReturn(updatedTrainee);

        // When
        UpdateTraineeResponse updateResponse = traineeService.update(request);

        // Then
        assertNotNull(updateResponse);
        assertEquals(username, updateResponse.username());
        assertEquals("John", updateResponse.firstName());
        assertEquals("Doe", updateResponse.lastName());
        assertEquals("New Address", updateResponse.address());
        assertTrue(updateResponse.isActive());

        verify(traineeRepository, times(1)).existsByUsername(username);
        verify(userService, times(1)).findByUsername(username);
        verify(userService, times(1)).update(any());
        verify(traineeRepository, times(1)).update(any());
    }

    @Test
    void testUpdateTraineeWithNullFields() {
        // Given
        String username = "john.doe";
        UpdateTraineeRequest request = new UpdateTraineeRequest(username, "John", "Doe", null, null, true);

        when(traineeRepository.existsByUsername(username)).thenReturn(true);

        User existingUser = new User("John", "Doe", username, "oldPassword", true);
        when(userService.findByUsername(username)).thenReturn(existingUser);

        Trainee existingTrainee = new Trainee(new Date(), "Old Address", existingUser, List.of());
        when(traineeRepository.findByUsername(username)).thenReturn(existingTrainee);

        User updatedUser = new User("John", "Doe", username, "oldPassword", true);
        when(userService.update(any())).thenReturn(updatedUser);

        Trainee updatedTrainee = new Trainee(new Date(), "Old Address", updatedUser, List.of());
        when(traineeRepository.update(any())).thenReturn(updatedTrainee);

        // When
        UpdateTraineeResponse updateResponse = traineeService.update(request);

        // Then
        assertNotNull(updateResponse);
        assertEquals(username, updateResponse.username());
        assertEquals("John", updateResponse.firstName());
        assertEquals("Doe", updateResponse.lastName());
        assertEquals("Old Address", updateResponse.address());
        assertTrue(updateResponse.isActive());

        verify(traineeRepository, times(1)).existsByUsername(username);
        verify(userService, times(1)).findByUsername(username);
        verify(userService, times(1)).update(any());
        verify(traineeRepository, times(1)).update(any());
    }

    @Test
    void testUpdateTraineeWhenTraineeDoesNotExist() {
        // Given
        String nonExistingUsername = "nonExistingUser";
        UpdateTraineeRequest request = new UpdateTraineeRequest(nonExistingUsername, "John", "Doe", new Date(), "New Address", true);

        when(traineeRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(TraineeNotFoundException.class, () -> traineeService.update(request));

        verify(traineeRepository, times(1)).existsByUsername(nonExistingUsername);
        verify(userService, never()).findByUsername(any());
        verify(userService, never()).update(any());
        verify(traineeRepository, never()).update(any());
    }

    @Test
    void testDeleteTraineeByUsername() {
        // Given
        String username = "john.doe";

        when(traineeRepository.existsByUsername(username)).thenReturn(true);

        Trainee existingTrainee = new Trainee(new Date(), "Old Address", new User("John", "Doe", username, "oldPassword", true), List.of());
        when(traineeRepository.findByUsername(username)).thenReturn(existingTrainee);

        // When
        traineeService.deleteByUsername(username);

        // Then
        verify(traineeRepository, times(1)).existsByUsername(username);
        verify(traineeRepository, times(1)).findByUsername(username);
        verify(traineeRepository, times(1)).delete(existingTrainee);
    }

    @Test
    void testDeleteTraineeByUsernameWhenTraineeDoesNotExist() {
        // Given
        String nonExistingUsername = "nonExistingUser";

        when(traineeRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(TraineeNotFoundException.class, () -> traineeService.deleteByUsername(nonExistingUsername));

        verify(traineeRepository, times(1)).existsByUsername(nonExistingUsername);
        verify(traineeRepository, never()).findByUsername(any());
        verify(traineeRepository, never()).delete(any());
    }

    @Test
    void testUpdateStatus() {
        // Given
        String username = "john.doe";
        StatusRequest request = new StatusRequest(username, true);

        when(traineeRepository.existsByUsername(username)).thenReturn(true);

        Trainee existingTrainee = new Trainee(new Date(), "Old Address", new User("John", "Doe", username, "oldPassword", false), List.of());
        when(traineeRepository.findByUsername(username)).thenReturn(existingTrainee);

        // When
        traineeService.updateStatus(request);

        // Then
        verify(traineeRepository, times(1)).existsByUsername(username);
        verify(traineeRepository, times(1)).findByUsername(username);
        verify(traineeRepository, times(1)).updateStatus(username, true);
    }

    @Test
    void testUpdateStatusWhenTraineeDoesNotExist() {
        // Given
        String nonExistingUsername = "nonExistingUser";
        StatusRequest request = new StatusRequest(nonExistingUsername, true);

        when(traineeRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(TraineeNotFoundException.class, () -> traineeService.updateStatus(request));

        verify(traineeRepository, times(1)).existsByUsername(nonExistingUsername);
        verify(traineeRepository, never()).findByUsername(any());
        verify(traineeRepository, never()).updateStatus(any(), anyBoolean());
    }

    @Test
    void testUpdateStatusWhenStatusIsAlreadySet() {
        // Given
        String username = "john.doe";
        StatusRequest request = new StatusRequest(username, true);

        when(traineeRepository.existsByUsername(username)).thenReturn(true);

        Trainee existingTrainee = new Trainee(new Date(), "Old Address", new User("John", "Doe", username, "oldPassword", true), List.of());
        when(traineeRepository.findByUsername(username)).thenReturn(existingTrainee);

        // When, Then
        assertThrows(RuntimeException.class, () -> traineeService.updateStatus(request));

        verify(traineeRepository, times(1)).existsByUsername(username);
        verify(traineeRepository, times(1)).findByUsername(username);
        verify(traineeRepository, never()).updateStatus(any(), anyBoolean());
    }
}
