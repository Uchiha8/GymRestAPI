package com.epam.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Date;

public class TraineeTrainingsResponseTest {
    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String trainingName = "Java Basics";
        Date trainingDate = new Date();
        String trainingType = "Java";
        Duration duration = Duration.ofHours(2);
        String trainerUsername = "trainerUsername";

        // When
        TraineeTrainingsResponse response = new TraineeTrainingsResponse(trainingName, trainingDate, trainingType, duration, trainerUsername);

        // Then
        assertEquals(trainingName, response.trainingName());
        assertEquals(trainingDate, response.trainingDate());
        assertEquals(trainingType, response.trainingType());
        assertEquals(duration, response.duration());
        assertEquals(trainerUsername, response.trainerUsername());
    }

    @Test
    public void testRecordToString() {
        // Given
        String trainingName = "Java Basics";
        Date trainingDate = new Date();
        String trainingType = "Java";
        Duration duration = Duration.ofHours(2);
        String trainerUsername = "trainerUsername";

        // When
        TraineeTrainingsResponse response = new TraineeTrainingsResponse(trainingName, trainingDate, trainingType, duration, trainerUsername);

        // Then
        String expectedToString = "TraineeTrainingsResponse[trainingName=" + trainingName +
                ", trainingDate=" + trainingDate +
                ", trainingType=" + trainingType +
                ", duration=" + duration +
                ", trainerUsername=" + trainerUsername + "]";
        assertEquals(expectedToString, response.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        TraineeTrainingsResponse response1 = new TraineeTrainingsResponse("Java Basics", new Date(), "Java", Duration.ofHours(2), "trainerUsername");
        TraineeTrainingsResponse response2 = new TraineeTrainingsResponse("Java Basics", new Date(), "Java", Duration.ofHours(2), "trainerUsername");

        // Then
        assertEquals(response1, response2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TraineeTrainingsResponse response1 = new TraineeTrainingsResponse("Java Basics", new Date(), "Java", Duration.ofHours(2), "trainerUsername");
        TraineeTrainingsResponse response2 = new TraineeTrainingsResponse("Python Basics", new Date(), "Python", Duration.ofHours(3), "differentTrainer");

        // Then
        assertNotEquals(response1, response2);
    }
}
