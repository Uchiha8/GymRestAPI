package com.epam.dto.response;

import com.epam.domain.TrainingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TraineeProfileResponseTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String address = "123 Main St";
        Boolean isActive = true;
        List<TrainersList> trainersList = new ArrayList<>();

        // When
        TraineeProfileResponse traineeProfileResponse = new TraineeProfileResponse(firstName, lastName,
                dateOfBirth, address, isActive, trainersList);

        // Then
        assertEquals(firstName, traineeProfileResponse.firstName());
        assertEquals(lastName, traineeProfileResponse.lastName());
        assertEquals(dateOfBirth, traineeProfileResponse.dateOfBirth());
        assertEquals(address, traineeProfileResponse.address());
        assertEquals(isActive, traineeProfileResponse.isActive());
        assertEquals(trainersList, traineeProfileResponse.trainersList());
    }

    @Test
    public void testRecordToString() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String address = "123 Main St";
        Boolean isActive = true;
        List<TrainersList> trainersList = new ArrayList<>();

        // When
        TraineeProfileResponse traineeProfileResponse = new TraineeProfileResponse(firstName, lastName,
                dateOfBirth, address, isActive, trainersList);

        // Then
        String expectedToString = "TraineeProfileResponse[firstName=" + firstName +
                ", lastName=" + lastName +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                ", isActive=" + isActive +
                ", trainersList=" + trainersList + "]";
        assertEquals(expectedToString, traineeProfileResponse.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        List<TrainersList> trainersList = new ArrayList<>();
        TraineeProfileResponse response1 = new TraineeProfileResponse("John", "Doe",
                new Date(), "123 Main St", true, trainersList);
        TraineeProfileResponse response2 = new TraineeProfileResponse("John", "Doe",
                new Date(), "123 Main St", true, trainersList);

        // Then
        assertEquals(response1, response2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        List<TrainersList> trainersList1 = new ArrayList<>();
        List<TrainersList> trainersList2 = new ArrayList<>();
        trainersList2.add(new TrainersList("Trainer1", "Jon", "Doe", new TrainingType("Java Programming")));

        TraineeProfileResponse response1 = new TraineeProfileResponse("John", "Doe",
                new Date(), "123 Main St", true, trainersList1);
        TraineeProfileResponse response2 = new TraineeProfileResponse("John", "Doe",
                new Date(), "123 Main St", true, trainersList2);

        // Then
        assertNotEquals(response1, response2);
    }
}

