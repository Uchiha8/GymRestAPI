package com.epam.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatusRequestTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "user123";
        Boolean isActive = true;

        // When
        StatusRequest statusRequest = new StatusRequest(username, isActive);

        // Then
        assertEquals(username, statusRequest.username());
        assertEquals(isActive, statusRequest.isActive());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "user123";
        Boolean isActive = true;

        // When
        StatusRequest statusRequest = new StatusRequest(username, isActive);

        // Then
        String expectedToString = "StatusRequest[username=" + username +
                ", isActive=" + isActive + "]";
        assertEquals(expectedToString, statusRequest.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        StatusRequest statusRequest1 = new StatusRequest("user123", true);
        StatusRequest statusRequest2 = new StatusRequest("user123", true);

        // Then
        assertEquals(statusRequest1, statusRequest2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        StatusRequest statusRequest1 = new StatusRequest("user123", true);
        StatusRequest statusRequest2 = new StatusRequest("differentUser", false);

        // Then
        assertNotEquals(statusRequest1, statusRequest2);
    }
}

