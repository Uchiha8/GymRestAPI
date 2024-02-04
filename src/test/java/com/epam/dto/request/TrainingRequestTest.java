package com.epam.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Date;

public class TrainingRequestTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String traineeUsername = "trainee123";
        String trainerUsername = "trainer456";
        String trainingName = "Java Basics";
        Date trainingDate = new Date(); // Replace with a valid date
        Duration duration = Duration.ofHours(2);

        // When
        TrainingRequest request = new TrainingRequest(traineeUsername, trainerUsername, trainingName, trainingDate, duration);

        // Then
        assertEquals(traineeUsername, request.traineeUsername());
        assertEquals(trainerUsername, request.trainerUsername());
        assertEquals(trainingName, request.trainingName());
        assertEquals(trainingDate, request.trainingDate());
        assertEquals(duration, request.duration());
    }

    @Test
    public void testRecordToString() {
        // Given
        String traineeUsername = "trainee123";
        String trainerUsername = "trainer456";
        String trainingName = "Java Basics";
        Date trainingDate = new Date(); // Replace with a valid date
        Duration duration = Duration.ofHours(2);

        // When
        TrainingRequest request = new TrainingRequest(traineeUsername, trainerUsername, trainingName, trainingDate, duration);

        // Then
        String expectedToString = "TrainingRequest[traineeUsername=" + traineeUsername +
                ", trainerUsername=" + trainerUsername +
                ", trainingName=" + trainingName +
                ", trainingDate=" + trainingDate +
                ", duration=" + duration + "]";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        String traineeUsername = "trainee123";
        String trainerUsername = "trainer456";
        String trainingName = "Java Basics";
        Date trainingDate = new Date(); // Replace with a valid date
        Duration duration = Duration.ofHours(2);

        TrainingRequest request1 = new TrainingRequest(traineeUsername, trainerUsername, trainingName, trainingDate, duration);
        TrainingRequest request2 = new TrainingRequest(traineeUsername, trainerUsername, trainingName, trainingDate, duration);

        // Then
        assertEquals(request1, request2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TrainingRequest request1 = new TrainingRequest("trainee123", "trainer456", "Java Basics", new Date(), Duration.ofHours(2));
        TrainingRequest request2 = new TrainingRequest("differentTrainee", "differentTrainer", "Python Basics", new Date(), Duration.ofHours(3));

        // Then
        assertNotEquals(request1, request2);
    }
}

