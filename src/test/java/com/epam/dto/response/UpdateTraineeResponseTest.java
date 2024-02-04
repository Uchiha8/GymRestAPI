package com.epam.dto.response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class UpdateTraineeResponseTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "traineeUsername";
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String address = "123 Main St";
        Boolean isActive = true;
        List<TrainersList> trainersList = new ArrayList<>();

        // When
        UpdateTraineeResponse response = new UpdateTraineeResponse(username, firstName, lastName, dateOfBirth, address, isActive, trainersList);

        // Then
        assertEquals(username, response.username());
        assertEquals(firstName, response.firstName());
        assertEquals(lastName, response.lastName());
        assertEquals(dateOfBirth, response.dateOfBirth());
        assertEquals(address, response.address());
        assertEquals(isActive, response.isActive());
        assertEquals(trainersList, response.trainersList());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "traineeUsername";
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String address = "123 Main St";
        Boolean isActive = true;
        List<TrainersList> trainersList = new ArrayList<>();

        // When
        UpdateTraineeResponse response = new UpdateTraineeResponse(username, firstName, lastName, dateOfBirth, address, isActive, trainersList);

        // Then
        String expectedToString = "UpdateTraineeResponse[username=" + username +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                ", isActive=" + isActive +
                ", trainersList=" + trainersList + "]";
        assertEquals(expectedToString, response.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        List<TrainersList> trainersList = new ArrayList<>();
        UpdateTraineeResponse response1 = new UpdateTraineeResponse("traineeUsername", "John", "Doe",
                new Date(), "123 Main St", true, trainersList);
        UpdateTraineeResponse response2 = new UpdateTraineeResponse("traineeUsername", "John", "Doe",
                new Date(), "123 Main St", true, trainersList);

        // Then
        assertEquals(response1, response2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        List<TrainersList> trainersList1 = new ArrayList<>();
        List<TrainersList> trainersList2 = new ArrayList<>();
        trainersList2.add(new TrainersList("trainerUsername", "Trainer", "User", null));

        UpdateTraineeResponse response1 = new UpdateTraineeResponse("traineeUsername", "John", "Doe",
                new Date(), "123 Main St", true, trainersList1);
        UpdateTraineeResponse response2 = new UpdateTraineeResponse("differentTrainee", "Jane", "Doe",
                new Date(), "456 Second St", false, trainersList2);

        // Then
        assertNotEquals(response1, response2);
    }
}
