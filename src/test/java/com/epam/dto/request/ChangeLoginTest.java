package com.epam.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeLoginTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "user123";
        String oldPassword = "oldPassword123";
        String newPassword = "newPassword456";

        // When
        ChangeLogin changeLoginRequest = new ChangeLogin(username, oldPassword, newPassword);

        // Then
        assertEquals(username, changeLoginRequest.username());
        assertEquals(oldPassword, changeLoginRequest.oldPassword());
        assertEquals(newPassword, changeLoginRequest.newPassword());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "user123";
        String oldPassword = "oldPassword123";
        String newPassword = "newPassword456";

        // When
        ChangeLogin changeLoginRequest = new ChangeLogin(username, oldPassword, newPassword);

        // Then
        String expectedToString = "ChangeLogin[username=" + username +
                ", oldPassword=" + oldPassword +
                ", newPassword=" + newPassword + "]";
        assertEquals(expectedToString, changeLoginRequest.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        ChangeLogin changeLoginRequest1 = new ChangeLogin("user123", "oldPassword123", "newPassword456");
        ChangeLogin changeLoginRequest2 = new ChangeLogin("user123", "oldPassword123", "newPassword456");

        // Then
        assertEquals(changeLoginRequest1, changeLoginRequest2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        ChangeLogin changeLoginRequest1 = new ChangeLogin("user123", "oldPassword123", "newPassword456");
        ChangeLogin changeLoginRequest2 = new ChangeLogin("differentUser", "differentPassword", "differentNewPassword");

        // Then
        assertNotEquals(changeLoginRequest1, changeLoginRequest2);
    }
}

