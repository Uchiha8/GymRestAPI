package com.epam.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.domain.TrainingType;

public class TrainerRegistrationRequestTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java Programming");

        // When
        TrainerRegistrationRequest request = new TrainerRegistrationRequest(firstName, lastName, trainingType);

        // Then
        assertEquals(firstName, request.firstName());
        assertEquals(lastName, request.lastName());
        assertEquals(trainingType, request.trainingType());
    }

    @Test
    public void testRecordToString() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java Programming");

        // When
        TrainerRegistrationRequest request = new TrainerRegistrationRequest(firstName, lastName, trainingType);

        // Then
        String expectedToString = "TrainerRegistrationRequest[firstName=" + firstName +
                ", lastName=" + lastName +
                ", trainingType=" + trainingType + "]";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        TrainingType trainingType = new TrainingType("Java Programming");
        TrainerRegistrationRequest request1 = new TrainerRegistrationRequest("John", "Doe", trainingType);
        TrainerRegistrationRequest request2 = new TrainerRegistrationRequest("John", "Doe", trainingType);

        // Then
        assertEquals(request1, request2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TrainingType trainingType1 = new TrainingType("Java Programming");
        TrainingType trainingType2 = new TrainingType("Python Programming");
        TrainerRegistrationRequest request1 = new TrainerRegistrationRequest("John", "Doe", trainingType1);
        TrainerRegistrationRequest request2 = new TrainerRegistrationRequest("Jane", "Doe", trainingType2);

        // Then
        assertNotEquals(request1, request2);
    }
}

