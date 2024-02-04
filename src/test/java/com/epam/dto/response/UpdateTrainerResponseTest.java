package com.epam.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.domain.TrainingType;

import java.util.ArrayList;
import java.util.List;

public class UpdateTrainerResponseTest {
    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "trainerUsername";
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java");
        Boolean isActive = true;
        List<TraineeList> traineeLists = new ArrayList<>();

        // When
        UpdateTrainerResponse response = new UpdateTrainerResponse(username, firstName, lastName, trainingType, isActive, traineeLists);

        // Then
        assertEquals(username, response.username());
        assertEquals(firstName, response.firstName());
        assertEquals(lastName, response.lastName());
        assertEquals(trainingType, response.trainingType());
        assertEquals(isActive, response.isActive());
        assertEquals(traineeLists, response.traineeLists());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "trainerUsername";
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java");
        Boolean isActive = true;
        List<TraineeList> traineeLists = new ArrayList<>();

        // When
        UpdateTrainerResponse response = new UpdateTrainerResponse(username, firstName, lastName, trainingType, isActive, traineeLists);

        // Then
        String expectedToString = "UpdateTrainerResponse[username=" + username +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", trainingType=" + trainingType +
                ", isActive=" + isActive +
                ", traineeLists=" + traineeLists + "]";
        assertEquals(expectedToString, response.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        List<TraineeList> traineeLists = new ArrayList<>();
        TrainingType trainingType = new TrainingType("Java");
        UpdateTrainerResponse response1 = new UpdateTrainerResponse("trainerUsername", "John", "Doe",
                trainingType, true, traineeLists);
        UpdateTrainerResponse response2 = new UpdateTrainerResponse("trainerUsername", "John", "Doe",
                trainingType, true, traineeLists);

        // Then
        assertEquals(response1, response2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        List<TraineeList> traineeLists1 = new ArrayList<>();
        List<TraineeList> traineeLists2 = new ArrayList<>();
        traineeLists2.add(new TraineeList("traineeUsername", "Trainee", "User"));

        UpdateTrainerResponse response1 = new UpdateTrainerResponse("trainerUsername", "John", "Doe",
                new TrainingType("Java"), true, traineeLists1);
        UpdateTrainerResponse response2 = new UpdateTrainerResponse("differentTrainer", "Jane", "Doe",
                new TrainingType("Python"), false, traineeLists2);

        // Then
        assertNotEquals(response1, response2);
    }
}
