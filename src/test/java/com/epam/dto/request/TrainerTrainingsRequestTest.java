package com.epam.dto.request;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
public class TrainerTrainingsRequestTest {
    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "trainerUsername";
        Date periodFrom = new Date();
        Date periodTo = new Date();
        String trainingType = "Java";
        String traineeUsername = "traineeUsername";

        // When
        TrainerTrainingsRequest request = new TrainerTrainingsRequest(username, periodFrom, periodTo, trainingType, traineeUsername);

        // Then
        assertEquals(username, request.username());
        assertEquals(periodFrom, request.periodFrom());
        assertEquals(periodTo, request.periodTo());
        assertEquals(trainingType, request.trainingType());
        assertEquals(traineeUsername, request.traineeUsername());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "trainerUsername";
        Date periodFrom = new Date();
        Date periodTo = new Date();
        String trainingType = "Java";
        String traineeUsername = "traineeUsername";

        // When
        TrainerTrainingsRequest request = new TrainerTrainingsRequest(username, periodFrom, periodTo, trainingType, traineeUsername);

        // Then
        String expectedToString = "TrainerTrainingsRequest[username=" + username +
                ", periodFrom=" + periodFrom +
                ", periodTo=" + periodTo +
                ", trainingType=" + trainingType +
                ", traineeUsername=" + traineeUsername + "]";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        Date periodFrom = new Date();
        TrainerTrainingsRequest request1 = new TrainerTrainingsRequest("trainerUsername",periodFrom,periodFrom, "Java", "traineeUsername");
        TrainerTrainingsRequest request2 = new TrainerTrainingsRequest("trainerUsername", periodFrom,periodFrom, "Java", "traineeUsername");

        // Then
        assertEquals(request1, request2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TrainerTrainingsRequest request1 = new TrainerTrainingsRequest("trainerUsername", new Date(), new Date(), "Java", "traineeUsername");
        TrainerTrainingsRequest request2 = new TrainerTrainingsRequest("differentTrainer", new Date(), new Date(), "Python", "differentTrainee");

        // Then
        assertNotEquals(request1, request2);
    }
}
