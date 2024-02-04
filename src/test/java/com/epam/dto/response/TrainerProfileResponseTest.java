package com.epam.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.domain.TrainingType;

import java.util.ArrayList;
import java.util.List;

public class TrainerProfileResponseTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java");
        Boolean isActive = true;
        List<TraineeList> traineeLists = new ArrayList<>();

        // When
        TrainerProfileResponse response = new TrainerProfileResponse(firstName, lastName, trainingType, isActive, traineeLists);

        // Then
        assertEquals(firstName, response.firstName());
        assertEquals(lastName, response.lastName());
        assertEquals(trainingType, response.trainingType());
        assertEquals(isActive, response.isActive());
        assertEquals(traineeLists, response.traineeLists());
    }

    @Test
    public void testRecordToString() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        TrainingType trainingType = new TrainingType("Java");
        Boolean isActive = true;
        List<TraineeList> traineeLists = new ArrayList<>();

        // When
        TrainerProfileResponse response = new TrainerProfileResponse(firstName, lastName, trainingType, isActive, traineeLists);

        // Then
        String expectedToString = "TrainerProfileResponse[firstName=" + firstName +
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
        TrainerProfileResponse response1 = new TrainerProfileResponse("John", "Doe",
                trainingType, true, traineeLists);
        TrainerProfileResponse response2 = new TrainerProfileResponse("John", "Doe",
                trainingType, true, traineeLists);

        // Then
        assertEquals(response1, response2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        List<TraineeList> traineeLists1 = new ArrayList<>();
        List<TraineeList> traineeLists2 = new ArrayList<>();
        traineeLists2.add(new TraineeList("Trainee1", "John", "Doe"));

        TrainerProfileResponse response1 = new TrainerProfileResponse("John", "Doe",
                new TrainingType("Java"), true, traineeLists1);
        TrainerProfileResponse response2 = new TrainerProfileResponse("John", "Doe",
                new TrainingType("Java"), true, traineeLists2);

        // Then
        assertNotEquals(response1, response2);
    }
}
