package com.epam.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TraineeListTest {

    @Test
    public void testRecordInstantiationAndGetters() {
        // Given
        String username = "johnDoe";
        String firstName = "John";
        String lastName = "Doe";

        // When
        TraineeList traineeList = new TraineeList(username, firstName, lastName);

        // Then
        assertEquals(username, traineeList.username());
        assertEquals(firstName, traineeList.firstName());
        assertEquals(lastName, traineeList.lastName());
    }

    @Test
    public void testRecordToString() {
        // Given
        String username = "johnDoe";
        String firstName = "John";
        String lastName = "Doe";

        // When
        TraineeList traineeList = new TraineeList(username, firstName, lastName);

        // Then
        String expectedToString = "TraineeList[username=" + username +
                ", firstName=" + firstName +
                ", lastName=" + lastName + "]";
        assertEquals(expectedToString, traineeList.toString());
    }

    @Test
    public void testRecordEquality() {
        // Given
        TraineeList traineeList1 = new TraineeList("johnDoe", "John", "Doe");
        TraineeList traineeList2 = new TraineeList("johnDoe", "John", "Doe");

        // Then
        assertEquals(traineeList1, traineeList2);
    }

    @Test
    public void testRecordInequality() {
        // Given
        TraineeList traineeList1 = new TraineeList("johnDoe", "John", "Doe");
        TraineeList traineeList2 = new TraineeList("differentUsername", "Jane", "Doe");

        // Then
        assertNotEquals(traineeList1, traineeList2);
    }
}

