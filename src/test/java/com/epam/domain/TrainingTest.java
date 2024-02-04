package com.epam.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

public class TrainingTest {

    @Test
    public void testParameterizedConstructor() {
        Trainee trainee = new Trainee();
        Trainer trainer = new Trainer();
        String trainingName = "Test Training";
        TrainingType trainingType = new TrainingType("Test Type");
        Date trainingDate = new Date();
        Duration duration = Duration.ofHours(1);

        Training training = new Training(trainee, trainer, trainingName, trainingType, trainingDate, duration);

        assertNotNull(training);
        assertNull(training.getId());
        assertEquals(trainee, training.getTrainee());
        assertEquals(trainer, training.getTrainer());
        assertEquals(trainingName, training.getTrainingName());
        assertEquals(trainingType, training.getTrainingType());
        assertEquals(trainingDate, training.getTrainingDate());
        assertEquals(duration, training.getDuration());
    }

    @Test
    public void testDefaultConstructor() {
        Training training = new Training();

        assertNotNull(training);
        assertNull(training.getId());
        assertNull(training.getTrainee());
        assertNull(training.getTrainer());
        assertNull(training.getTrainingName());
        assertNull(training.getTrainingType());
        assertNull(training.getTrainingDate());
        assertNull(training.getDuration());
    }

    @Test
    public void testGettersAndSetters() {
        Training training = new Training();
        Long id = 1L;
        Trainee trainee = new Trainee();
        Trainer trainer = new Trainer();
        String trainingName = "Test Training";
        TrainingType trainingType = new TrainingType("Test Type");
        Date trainingDate = new Date();
        Duration duration = Duration.ofHours(1);

        training.setId(id);
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName(trainingName);
        training.setTrainingType(trainingType);
        training.setTrainingDate(trainingDate);
        training.setDuration(duration);

        assertEquals(id, training.getId());
        assertEquals(trainee, training.getTrainee());
        assertEquals(trainer, training.getTrainer());
        assertEquals(trainingName, training.getTrainingName());
        assertEquals(trainingType, training.getTrainingType());
        assertEquals(trainingDate, training.getTrainingDate());
        assertEquals(duration, training.getDuration());
    }

    @Test
    public void testToString() {
        Training training = new Training();
        training.setId(1L);
        Trainee trainee = new Trainee();
        training.setTrainee(trainee);
        Trainer trainer = new Trainer();
        training.setTrainer(trainer);
        training.setTrainingName("Test Training");
        TrainingType trainingType = new TrainingType("Test Type");
        training.setTrainingType(trainingType);
        Date trainingDate = new Date();
        training.setTrainingDate(trainingDate);
        Duration duration = Duration.ofHours(1);
        training.setDuration(duration);

        String expectedToString = "Training{id=1, trainee=" + trainee + ", trainer=" + trainer +
                ", trainingName='Test Training', trainingType=" + trainingType +
                ", trainingDate=" + trainingDate + ", duration=" + duration + '}';
        assertEquals(expectedToString, training.toString());
    }
}

