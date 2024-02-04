package com.epam.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.domain.TrainingType;

public class TrainersListTest {
    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "trainerUsername";
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java");

        // When
        TrainersList trainersList = new TrainersList(username, firstName, lastName, trainingType);

        // Then
        assertEquals(username, trainersList.username());
        assertEquals(firstName, trainersList.firstName());
        assertEquals(lastName, trainersList.lastName());
        assertEquals(trainingType, trainersList.trainingType());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "trainerUsername";
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java");

        // When
        TrainersList trainersList = new TrainersList(username, firstName, lastName, trainingType);

        // Then
        String expectedToString = "TrainersList[username=" + username +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", trainingType=" + trainingType + "]";
        assertEquals(expectedToString, trainersList.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        TrainingType trainingType = new TrainingType("Java");
        TrainersList trainersList1 = new TrainersList("trainerUsername", "John", "Doe", trainingType);
        TrainersList trainersList2 = new TrainersList("trainerUsername", "John", "Doe", trainingType);

        // Then
        assertEquals(trainersList1, trainersList2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TrainersList trainersList1 = new TrainersList("trainerUsername", "John", "Doe", new TrainingType("Java"));
        TrainersList trainersList2 = new TrainersList("differentTrainer", "Jane", "Doe", new TrainingType("Python"));

        // Then
        assertNotEquals(trainersList1, trainersList2);
    }
}
