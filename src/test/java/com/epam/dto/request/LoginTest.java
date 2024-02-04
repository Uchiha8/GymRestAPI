package com.epam.dto.request;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "user123";
        String password = "password123";

        // When
        Login loginRequest = new Login(username, password);

        // Then
        assertEquals(username, loginRequest.username());
        assertEquals(password, loginRequest.password());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "user123";
        String password = "password123";

        // When
        Login loginRequest = new Login(username, password);

        // Then
        String expectedToString = "Login[username=" + username +
                ", password=" + password + "]";
        assertEquals(expectedToString, loginRequest.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        Login loginRequest1 = new Login("user123", "password123");
        Login loginRequest2 = new Login("user123", "password123");

        // Then
        assertEquals(loginRequest1, loginRequest2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        Login loginRequest1 = new Login("user123", "password123");
        Login loginRequest2 = new Login("differentUser", "differentPassword");

        // Then
        assertNotEquals(loginRequest1, loginRequest2);
    }
}

