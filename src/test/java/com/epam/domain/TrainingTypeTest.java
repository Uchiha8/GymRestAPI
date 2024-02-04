package com.epam.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TrainingTypeTest {

    @Test
    public void testParameterizedConstructor() {
        String typeName = "Test Type";

        TrainingType trainingType = new TrainingType(typeName);

        assertNotNull(trainingType);
        assertNull(trainingType.getId());
        assertEquals(typeName, trainingType.getName());
    }

    @Test
    public void testDefaultConstructor() {
        TrainingType trainingType = new TrainingType();

        assertNotNull(trainingType);
        assertNull(trainingType.getId());
        assertNull(trainingType.getName());
    }

    @Test
    public void testGettersAndSetters() {
        TrainingType trainingType = new TrainingType();
        Long id = 1L;
        String typeName = "Test Type";

        trainingType.setId(id);
        trainingType.setName(typeName);

        assertEquals(id, trainingType.getId());
        assertEquals(typeName, trainingType.getName());
    }

    @Test
    public void testToString() {
        TrainingType trainingType = new TrainingType();
        trainingType.setId(1L);
        trainingType.setName("Test Type");

        String expectedToString = "TrainingType{id=1, name='Test Type'}";
        assertEquals(expectedToString, trainingType.toString());
    }
}

