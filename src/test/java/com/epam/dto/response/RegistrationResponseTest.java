package com.epam.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationResponseTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "johnDoe";
        String password = "securePassword";

        // When
        RegistrationResponse response = new RegistrationResponse(username, password);

        // Then
        assertEquals(username, response.username());
        assertEquals(password, response.password());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "johnDoe";
        String password = "securePassword";

        // When
        RegistrationResponse response = new RegistrationResponse(username, password);

        // Then
        String expectedToString = "RegistrationResponse[username=" + username +
                ", password=" + password + "]";
        assertEquals(expectedToString, response.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        RegistrationResponse response1 = new RegistrationResponse("johnDoe", "securePassword");
        RegistrationResponse response2 = new RegistrationResponse("johnDoe", "securePassword");

        // Then
        assertEquals(response1, response2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        RegistrationResponse response1 = new RegistrationResponse("johnDoe", "securePassword");
        RegistrationResponse response2 = new RegistrationResponse("differentUsername", "differentPassword");

        // Then
        assertNotEquals(response1, response2);
    }
}

