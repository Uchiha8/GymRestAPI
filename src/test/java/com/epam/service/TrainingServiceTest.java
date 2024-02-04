package com.epam.service;

import com.epam.domain.*;
import com.epam.dto.request.TrainingRequest;
import com.epam.repository.TraineeRepository;
import com.epam.repository.TrainerRepository;
import com.epam.repository.TrainingRepository;
import com.epam.utils.exception.TraineeNotFoundException;
import com.epam.utils.exception.TrainerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTraining() {
        // Given
        String traineeUsername = "john.ali";
        String trainerUsername = "john.doe";
        String trainingName = "JavaTraining";
        Date date = new Date();
        Duration duration = Duration.ofHours(2);

        when(traineeRepository.existsByUsername(traineeUsername)).thenReturn(true);
        when(trainerRepository.existsByUsername(trainerUsername)).thenReturn(true);
        when(trainingRepository.existsByName(trainingName)).thenReturn(false);

        TrainingType trainingType = new TrainingType("Java");
        User user = new User("John", "Doe", "john.doe", "password", true);
        User user1 = new User("John", "Ali", "john.ali", "password", false);
        Trainer trainer = new Trainer(trainingType, user, new ArrayList<>());
        Trainee trainee = new Trainee(new Date(), "Address", user1, new ArrayList<>());
        when(trainerRepository.findByUsername(trainerUsername)).thenReturn(trainer);
        when(traineeRepository.findByUsername(traineeUsername)).thenReturn(trainee);
        when(trainingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Training result = trainingService.save(new TrainingRequest(traineeUsername, trainerUsername, trainingName, date, duration));

        // Then
        assertNotNull(result);
        assertEquals(traineeUsername, result.getTrainee().getUser().getUsername());
        assertEquals(trainerUsername, result.getTrainer().getUser().getUsername());
        assertEquals(trainingName, result.getTrainingName());
        assertEquals(trainingType, result.getTrainingType());
        assertEquals(duration, result.getDuration());

        verify(traineeRepository, times(1)).existsByUsername(traineeUsername);
        verify(trainerRepository, times(1)).existsByUsername(trainerUsername);
        verify(trainingRepository, times(1)).existsByName(trainingName);
        verify(trainerRepository, times(2)).findByUsername(trainerUsername);
        verify(traineeRepository, times(1)).findByUsername(traineeUsername);
        verify(trainingRepository, times(1)).save(any());
    }

    @Test
    void testSaveTrainingWithNonExistingTrainee() {
        // Given
        String nonExistingTraineeUsername = "nonExistingTrainee";
        String trainerUsername = "trainerUser";
        String trainingName = "JavaTraining";
        Date date = new Date();
        Duration duration = Duration.ofHours(2);

        when(traineeRepository.existsByUsername(nonExistingTraineeUsername)).thenReturn(false);

        // When, Then
        assertThrows(TraineeNotFoundException.class, () ->
                trainingService.save(new TrainingRequest(nonExistingTraineeUsername, trainerUsername, trainingName, date, duration)));

        verify(traineeRepository, times(1)).existsByUsername(nonExistingTraineeUsername);
        verifyNoInteractions(trainerRepository);
        verifyNoInteractions(trainingRepository);
    }

    @Test
    void testSaveTrainingWithNonExistingTrainer() {
        // Given
        String traineeUsername = "traineeUser";
        String nonExistingTrainerUsername = "nonExistingTrainer";
        String trainingName = "JavaTraining";
        Date trainingDate = new Date();
        Duration duration = Duration.ofHours(2);
        when(traineeRepository.existsByUsername(traineeUsername)).thenReturn(true);
        when(trainerRepository.existsByUsername(nonExistingTrainerUsername)).thenReturn(false);

        // When, Then
        assertThrows(TrainerNotFoundException.class, () ->
                trainingService.save(new TrainingRequest(traineeUsername, nonExistingTrainerUsername, trainingName, trainingDate, duration)));

        verify(traineeRepository, times(1)).existsByUsername(traineeUsername);
        verify(trainerRepository, times(1)).existsByUsername(nonExistingTrainerUsername);
        verifyNoInteractions(trainingRepository);
    }

    @Test
    void testSaveTrainingWithExistingName() {
        // Given
        String traineeUsername = "traineeUser";
        String trainerUsername = "trainerUser";
        String existingTrainingName = "ExistingTraining";
        Date trainingDate = new Date();
        Duration duration = Duration.ofHours(2);

        when(traineeRepository.existsByUsername(traineeUsername)).thenReturn(true);
        when(trainerRepository.existsByUsername(trainerUsername)).thenReturn(true);
        when(trainingRepository.existsByName(existingTrainingName)).thenReturn(true);

        // When, Then
        assertThrows(IllegalArgumentException.class, () ->
                trainingService.save(new TrainingRequest(traineeUsername, trainerUsername, existingTrainingName, trainingDate, duration)));

        verify(traineeRepository, times(1)).existsByUsername(traineeUsername);
        verify(trainerRepository, times(1)).existsByUsername(trainerUsername);
        verify(trainingRepository, times(1)).existsByName(existingTrainingName);
        verifyNoMoreInteractions(trainerRepository, trainingRepository);
    }
}
