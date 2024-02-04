package com.epam.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class TraineeRegistrationRequestTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date(); // Replace with a valid date
        String address = "123 Main St";

        // When
        TraineeRegistrationRequest request = new TraineeRegistrationRequest(firstName, lastName, dateOfBirth, address);

        // Then
        assertEquals(firstName, request.firstName());
        assertEquals(lastName, request.lastName());
        assertEquals(dateOfBirth, request.dateOfBirth());
        assertEquals(address, request.address());
    }

    @Test
    public void testRecordToString() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date(); // Replace with a valid date
        String address = "123 Main St";

        // When
        TraineeRegistrationRequest request = new TraineeRegistrationRequest(firstName, lastName, dateOfBirth, address);

        // Then
        String expectedToString = "TraineeRegistrationRequest[firstName=" + firstName +
                ", lastName=" + lastName +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address + "]";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        TraineeRegistrationRequest request1 = new TraineeRegistrationRequest("John", "Doe", new Date(), "123 Main St");
        TraineeRegistrationRequest request2 = new TraineeRegistrationRequest("John", "Doe", new Date(), "123 Main St");

        // Then
        assertEquals(request1, request2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TraineeRegistrationRequest request1 = new TraineeRegistrationRequest("John", "Doe", new Date(), "123 Main St");
        TraineeRegistrationRequest request2 = new TraineeRegistrationRequest("Jane", "Doe", new Date(), "456 Oak St");

        // Then
        assertNotEquals(request1, request2);
    }
}

