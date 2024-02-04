package com.epam.dto.response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Date;
public class TrainerTrainingsResponseTest {
    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String trainingName = "Java Advanced";
        Date trainingDate = new Date();
        String trainingType = "Java";
        Duration duration = Duration.ofHours(3);
        String traineeUsername = "traineeUsername";

        // When
        TrainerTrainingsResponse response = new TrainerTrainingsResponse(trainingName, trainingDate, trainingType, duration, traineeUsername);

        // Then
        assertEquals(trainingName, response.trainingName());
        assertEquals(trainingDate, response.trainingDate());
        assertEquals(trainingType, response.trainingType());
        assertEquals(duration, response.duration());
        assertEquals(traineeUsername, response.traineeUsername());
    }

    @Test
    public void testRecordToString() {
        // Given
        String trainingName = "Java Advanced";
        Date trainingDate = new Date();
        String trainingType = "Java";
        Duration duration = Duration.ofHours(3);
        String traineeUsername = "traineeUsername";

        // When
        TrainerTrainingsResponse response = new TrainerTrainingsResponse(trainingName, trainingDate, trainingType, duration, traineeUsername);

        // Then
        String expectedToString = "TrainerTrainingsResponse[trainingName=" + trainingName +
                ", trainingDate=" + trainingDate +
                ", trainingType=" + trainingType +
                ", duration=" + duration +
                ", traineeUsername=" + traineeUsername + "]";
        assertEquals(expectedToString, response.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        TrainerTrainingsResponse response1 = new TrainerTrainingsResponse("Java Advanced", new Date(), "Java", Duration.ofHours(3), "traineeUsername");
        TrainerTrainingsResponse response2 = new TrainerTrainingsResponse("Java Advanced", new Date(), "Java", Duration.ofHours(3), "traineeUsername");

        // Then
        assertEquals(response1, response2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TrainerTrainingsResponse response1 = new TrainerTrainingsResponse("Java Advanced", new Date(), "Java", Duration.ofHours(3), "traineeUsername");
        TrainerTrainingsResponse response2 = new TrainerTrainingsResponse("Python Advanced", new Date(), "Python", Duration.ofHours(4), "differentTrainee");

        // Then
        assertNotEquals(response1, response2);
    }
}
