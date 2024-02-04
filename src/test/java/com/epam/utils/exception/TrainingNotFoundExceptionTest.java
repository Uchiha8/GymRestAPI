package com.epam.utils.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingNotFoundExceptionTest {
    @Test
    void testConstructorAndGetMessage() {
        // Given
        String errorMessage = "Training not found";

        // When
        TrainingNotFoundException exception = new TrainingNotFoundException(errorMessage);

        // Then
        assertEquals(errorMessage, exception.getMessage());
    }
}
