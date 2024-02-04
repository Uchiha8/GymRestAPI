package com.epam.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class UpdateTraineeRequestTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "trainee123";
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date(); // Replace with a valid date
        String address = "123 Main St";
        Boolean isActive = true;

        // When
        UpdateTraineeRequest request = new UpdateTraineeRequest(username, firstName, lastName, dateOfBirth, address, isActive);

        // Then
        assertEquals(username, request.username());
        assertEquals(firstName, request.firstName());
        assertEquals(lastName, request.lastName());
        assertEquals(dateOfBirth, request.dateOfBirth());
        assertEquals(address, request.address());
        assertEquals(isActive, request.isActive());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "trainee123";
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date(); // Replace with a valid date
        String address = "123 Main St";
        Boolean isActive = true;

        // When
        UpdateTraineeRequest request = new UpdateTraineeRequest(username, firstName, lastName, dateOfBirth, address, isActive);

        // Then
        String expectedToString = "UpdateTraineeRequest[username=" + username +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                ", isActive=" + isActive + "]";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        UpdateTraineeRequest request1 = new UpdateTraineeRequest("trainee123", "John", "Doe", new Date(), "123 Main St", true);
        UpdateTraineeRequest request2 = new UpdateTraineeRequest("trainee123", "John", "Doe", new Date(), "123 Main St", true);

        // Then
        assertEquals(request1, request2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        UpdateTraineeRequest request1 = new UpdateTraineeRequest("trainee123", "John", "Doe", new Date(), "123 Main St", true);
        UpdateTraineeRequest request2 = new UpdateTraineeRequest("differentTrainee", "Jane", "Doe", new Date(), "456 Oak St", false);

        // Then
        assertNotEquals(request1, request2);
    }
}

