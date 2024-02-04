package com.epam.service;

import com.epam.domain.*;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.TrainerTrainingsRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.request.StatusRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.dto.response.TrainerProfileResponse;
import com.epam.dto.response.UpdateTrainerResponse;
import com.epam.dto.response.TrainersList;
import com.epam.dto.response.TrainerTrainingsResponse;
import com.epam.repository.TrainerRepository;
import com.epam.utils.exception.TraineeNotFoundException;
import com.epam.utils.exception.TrainerNotFoundException;
import com.epam.utils.exception.TrainingTypeNotFoundException;
import com.epam.service.TrainerService;
import com.epam.service.TrainingTypeService;
import com.epam.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {
    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainingTypeService trainingTypeService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTrainer() {
        // Given
        TrainerRegistrationRequest request = new TrainerRegistrationRequest("John", "Doe", new TrainingType("Java"));
        User user = new User("John", "Doe", "john.doe", "password", false);
        when(userService.save(any())).thenReturn(user);
        when(trainingTypeService.findByName("Java")).thenReturn(new TrainingType("Java"));
        when(trainerRepository.save(any())).thenReturn(new Trainer(new TrainingType("Java"), user, List.of()));
        // When
        RegistrationResponse result = trainerService.save(request);

        // Then
        assertNotNull(result);
        assertNotNull(result.username());
        assertNotNull(result.password());

        verify(userService, times(1)).save(any());
        verify(trainingTypeService, times(1)).findByName("Java");
        verify(trainerRepository, times(1)).save(any());
    }

    @Test
    void testSaveTrainerWithNonExistingTrainingType() {
        // Given
        TrainerRegistrationRequest request = new TrainerRegistrationRequest("John", "Doe", new TrainingType("NonExistingType"));

        when(userService.save(any())).thenReturn(new User("John", "Doe", "john.doe", "password", false));
        when(trainingTypeService.findByName("NonExistingType")).thenThrow(new TrainingTypeNotFoundException("Training type not found"));

        // When, Then
        assertThrows(RuntimeException.class, () -> trainerService.save(request));

        verify(userService, times(1)).save(any());
        verify(trainingTypeService, times(1)).findByName("NonExistingType");
        verify(trainerRepository, never()).save(any());
    }

    @Test
    void testFindByUsername() {
        // Given
        String username = "john.doe";
        Trainer trainer = new Trainer(new TrainingType("Java"), new User("John", "Doe", username, "password", true), List.of());

        when(trainerRepository.existsByUsername(username)).thenReturn(true);
        when(trainerRepository.findByUsername(username)).thenReturn(trainer);

        // When
        TrainerProfileResponse result = trainerService.findByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals("John", result.firstName());
        assertEquals("Doe", result.lastName());
        assertEquals("Java", result.trainingType().getName());

        verify(trainerRepository, times(1)).existsByUsername(username);
        verify(trainerRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByUsernameWhenTrainerDoesNotExist() {
        // Given
        String nonExistingUsername = "nonExistingUser";

        when(trainerRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(RuntimeException.class, () -> trainerService.findByUsername(nonExistingUsername));

        verify(trainerRepository, times(1)).existsByUsername(nonExistingUsername);
        verify(trainerRepository, never()).findByUsername(any());
    }

    @Test
    void testUpdateTrainer() {
        // Given
        String username = "john.doe";
        UpdateTrainerRequest request = new UpdateTrainerRequest(username, "John", "Doe", new TrainingType("Java"), true);
        User user = new User("John", "Doe", username, "password", true);
        when(trainerRepository.existsByUsername(username)).thenReturn(true);
        when(userService.findByUsername(username)).thenReturn(user);
        when(trainingTypeService.findByName("Java")).thenReturn(new TrainingType("Java"));
        when(trainerRepository.update(any())).thenReturn(new Trainer(new TrainingType("Java"), user, List.of()));
        when(trainerRepository.findByUsername(username)).thenReturn(new Trainer(new TrainingType("Java"), user, List.of()));
        // When
        UpdateTrainerResponse result = trainerService.update(request);
        // Then
        assertNotNull(result);
        assertEquals("John", result.firstName());
        assertEquals("Doe", result.lastName());
        assertEquals("Java", result.trainingType().getName());

        verify(trainerRepository, times(1)).existsByUsername(username);
        verify(userService, times(1)).findByUsername(username);
        verify(trainingTypeService, times(1)).findByName("Java");
        verify(trainerRepository, times(1)).update(any());
    }

    @Test
    void testUpdateTrainerWithNonExistingTrainer() {
        // Given
        String nonExistingUsername = "nonExistingUser";
        UpdateTrainerRequest request = new UpdateTrainerRequest(nonExistingUsername, "John", "Doe", new TrainingType("Java"), true);

        when(trainerRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(TrainerNotFoundException.class, () -> trainerService.update(request));

        verify(trainerRepository, times(1)).existsByUsername(nonExistingUsername);
        verify(userService, never()).findByUsername(any());
        verify(trainingTypeService, never()).findByName(any());
        verify(trainerRepository, never()).update(any());
    }

    @Test
    void testUpdateTrainerWithNonExistingTrainingType() {
        // Given
        String username = "john.doe";
        UpdateTrainerRequest request = new UpdateTrainerRequest(username, "John", "Doe", new TrainingType("NonExistingType"), true);
        User user = new User("John", "Doe", username, "password", true);
        when(trainerRepository.existsByUsername(username)).thenReturn(true);
        when(userService.findByUsername(username)).thenReturn(user);
        when(trainingTypeService.findByName("NonExistingType")).thenThrow(new TrainingTypeNotFoundException("Training type not found"));
        when(trainerRepository.update(any())).thenReturn(new Trainer(new TrainingType("Java"), user, List.of()));
        // When, Then
        assertThrows(RuntimeException.class, () -> trainerService.update(request));

        verify(trainerRepository, times(1)).existsByUsername(username);
        verify(userService, times(1)).findByUsername(username);
        verify(trainingTypeService, times(0)).findByName("NonExistingType");
        verify(trainerRepository, never()).update(any());
    }

    @Test
    void testActiveTrainers() {
        // Given
        String username = "john.doe";
        Trainer trainer1 = new Trainer(new TrainingType("Java"), new User("Trainer1", "Trainer1", "trainer1", "password", true), List.of());
        Trainer trainer2 = new Trainer(new TrainingType("Java"), new User("Trainer2", "Trainer2", "trainer2", "password", true), List.of());
        Trainer trainer3 = new Trainer(new TrainingType("Java"), new User("Trainer3", "Trainer3", "trainer3", "password", true), List.of());

        List<Trainer> trainers = Arrays.asList(trainer1, trainer2, trainer3);

        when(trainerRepository.activeTrainers()).thenReturn(trainers);
        when(userService.existsByUsername(username)).thenReturn(true);

        // When
        List<TrainersList> result = trainerService.activeTrainers(username);

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());

        verify(trainerRepository, times(1)).activeTrainers();
        verify(userService, times(1)).existsByUsername(username);
    }

    @Test
    void testActiveTrainersWithNonExistingUser() {
        // Given
        String nonExistingUsername = "nonExistingUser";

        when(userService.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(RuntimeException.class, () -> trainerService.activeTrainers(nonExistingUsername));

        verify(trainerRepository, never()).activeTrainers();
        verify(userService, times(1)).existsByUsername(nonExistingUsername);
    }

    @Test
    void testUpdateStatus() {
        // Given
        String username = "john.doe";
        StatusRequest request = new StatusRequest(username, true);
        User user = new User("John", "Doe", username, "password", false);
        Trainer trainer = new Trainer(new TrainingType("Java"), user, List.of());
        when(trainerRepository.existsByUsername(username)).thenReturn(true);
        when(trainerRepository.findByUsername(username)).thenReturn(trainer);
        // When
        trainerService.updateStatus(request);

        // Then
        verify(trainerRepository, times(1)).existsByUsername(username);
        verify(trainerRepository, times(1)).updateStatus(username, true);
    }

    @Test
    void testUpdateStatusWithNonExistingTrainer() {
        // Given
        String nonExistingUsername = "nonExistingUser";
        StatusRequest request = new StatusRequest(nonExistingUsername, true);

        when(trainerRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(RuntimeException.class, () -> trainerService.updateStatus(request));

        verify(trainerRepository, times(1)).existsByUsername(nonExistingUsername);
        verify(trainerRepository, never()).updateStatus(any(), anyBoolean());
    }

    @Test
    void testReadTrainerTrainings() {
        // Given
        String username = "john.doe";
        User user = new User("John", "Doe", username, "password", true);
        Trainee trainee = new Trainee(new Date(), "Address", user, List.of());
        TrainerTrainingsRequest request = new TrainerTrainingsRequest(username, null, null, "Java", trainee.getUser().getUsername());

        TrainingType trainingType = new TrainingType("Java");
        User user1 = new User("John", "Doe", username, "password", true);
        Trainer trainer = new Trainer(trainingType, user1, List.of());

        Training training1 = new Training(trainee, trainer, "Training1", trainingType, new Date(), Duration.ofHours(1));
        Training training2 = new Training(trainee, trainer, "Training2", trainingType, new Date(), Duration.ofHours(2));

        List<Training> trainings = Arrays.asList(training1, training2);

        when(trainerRepository.existsByUsername(username)).thenReturn(true);
        when(trainerRepository.getTrainings(username)).thenReturn(trainings);
        when(userService.existsByUsername(username)).thenReturn(true);
        when(userService.findByUsername(username)).thenReturn(user);
        when(trainingTypeService.existsByName("Java")).thenReturn(true);

        // When
        List<TrainerTrainingsResponse> result = trainerService.readTrainerTrainings(request);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(trainerRepository, times(1)).existsByUsername(username);
        verify(trainerRepository, times(1)).getTrainings(username);
        verify(userService, times(1)).existsByUsername(username);
    }

    @Test
    void testReadTrainerTrainingsWithNonExistingTrainer() {
        // Given
        String nonExistingUsername = "nonExistingUser";
        TrainerTrainingsRequest request = new TrainerTrainingsRequest(nonExistingUsername, null, null, "Java", null);

        when(trainerRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // When, Then
        assertThrows(TrainerNotFoundException.class, () -> trainerService.readTrainerTrainings(request));

        verify(trainerRepository, times(1)).existsByUsername(nonExistingUsername);
        verify(trainerRepository, never()).getTrainings(any());
        verify(userService, never()).existsByUsername(any());
        verify(trainingTypeService, never()).existsByName(any());
    }

    @Test
    void testReadTrainerTrainingsWithNonExistingTrainingType() {
        // Given
        String username = "john.doe";
        TrainerTrainingsRequest request = new TrainerTrainingsRequest(username, new Date(), new Date(), "NonExistingType", null);

        when(trainerRepository.existsByUsername(username)).thenReturn(true);
        when(userService.existsByUsername(username)).thenReturn(true);
        when(trainingTypeService.existsByName("NonExistingType")).thenReturn(false);

        when(trainerRepository.existsByUsername(username)).thenReturn(true);
        when(userService.existsByUsername(username)).thenReturn(true);
        when(trainingTypeService.existsByName("NonExistingType")).thenReturn(false);

        // When, Then
        assertThrows(RuntimeException.class, () -> trainerService.readTrainerTrainings(request));

        verify(trainerRepository, times(1)).existsByUsername(username);
        verify(userService, times(0)).existsByUsername(username);
        verify(trainingTypeService, times(1)).existsByName("NonExistingType");
    }
}
