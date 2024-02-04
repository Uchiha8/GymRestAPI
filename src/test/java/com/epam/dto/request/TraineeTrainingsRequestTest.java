package com.epam.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class TraineeTrainingsRequestTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "user123";
        Date periodFrom = new Date(); // Replace with a valid date
        Date periodTo = new Date(); // Replace with a valid date
        String trainingType = "Java Programming";
        String trainerUsername = "trainer456";

        // When
        TraineeTrainingsRequest request = new TraineeTrainingsRequest(username, periodFrom, periodTo, trainingType, trainerUsername);

        // Then
        assertEquals(username, request.username());
        assertEquals(periodFrom, request.periodFrom());
        assertEquals(periodTo, request.periodTo());
        assertEquals(trainingType, request.trainingType());
        assertEquals(trainerUsername, request.trainerUsername());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "user123";
        Date periodFrom = new Date(); // Replace with a valid date
        Date periodTo = new Date(); // Replace with a valid date
        String trainingType = "Java Programming";
        String trainerUsername = "trainer456";

        // When
        TraineeTrainingsRequest request = new TraineeTrainingsRequest(username, periodFrom, periodTo, trainingType, trainerUsername);

        // Then
        String expectedToString = "TraineeTrainingsRequest[username=" + username +
                ", periodFrom=" + periodFrom +
                ", periodTo=" + periodTo +
                ", trainingType=" + trainingType +
                ", trainerUsername=" + trainerUsername + "]";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        TraineeTrainingsRequest request1 = new TraineeTrainingsRequest("user123", new Date(), new Date(), "Java Programming", "trainer456");
        TraineeTrainingsRequest request2 = new TraineeTrainingsRequest("user123", new Date(), new Date(), "Java Programming", "trainer456");

        // Then
        assertEquals(request1, request2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TraineeTrainingsRequest request1 = new TraineeTrainingsRequest("user123", new Date(), new Date(), "Java Programming", "trainer456");
        TraineeTrainingsRequest request2 = new TraineeTrainingsRequest("differentUser", new Date(), new Date(), "Python Programming", "differentTrainer");

        // Then
        assertNotEquals(request1, request2);
    }
}

