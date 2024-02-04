package com.epam.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.domain.TrainingType;

public class UpdateTrainerRequestTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "trainer123";
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java Programming");
        Boolean isActive = true;

        // When
        UpdateTrainerRequest request = new UpdateTrainerRequest(username, firstName, lastName, trainingType, isActive);

        // Then
        assertEquals(username, request.username());
        assertEquals(firstName, request.firstName());
        assertEquals(lastName, request.lastName());
        assertEquals(trainingType, request.trainingType());
        assertEquals(isActive, request.isActive());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "trainer123";
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java Programming");
        Boolean isActive = true;

        // When
        UpdateTrainerRequest request = new UpdateTrainerRequest(username, firstName, lastName, trainingType, isActive);

        // Then
        String expectedToString = "UpdateTrainerRequest[username=" + username +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", trainingType=" + trainingType +
                ", isActive=" + isActive + "]";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        TrainingType trainingType = new TrainingType("Java Programming");
        UpdateTrainerRequest request1 = new UpdateTrainerRequest("trainer123", "John", "Doe", trainingType, true);
        UpdateTrainerRequest request2 = new UpdateTrainerRequest("trainer123", "John", "Doe", trainingType, true);

        // Then
        assertEquals(request1, request2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TrainingType trainingType1 = new TrainingType("Java Programming");
        TrainingType trainingType2 = new TrainingType("Python Programming");
        UpdateTrainerRequest request1 = new UpdateTrainerRequest("trainer123", "John", "Doe", trainingType1, true);
        UpdateTrainerRequest request2 = new UpdateTrainerRequest("differentTrainer", "Jane", "Doe", trainingType2, false);

        // Then
        assertNotEquals(request1, request2);
    }
}

