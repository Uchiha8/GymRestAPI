package com.epam.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TrainerTest {

    @Test
    public void testParameterizedConstructor() {
        TrainingType trainingType = new TrainingType("Test Type");
        User user = new User(); // You need to create a valid User object
        List<Training> trainings = new ArrayList<>(); // You need to create valid Training objects

        Trainer trainer = new Trainer(trainingType, user, trainings);

        assertNotNull(trainer);
        assertNull(trainer.getId()); // Since it's generated, it should be null
        assertEquals(trainingType, trainer.getTrainingType());
        assertEquals(user, trainer.getUser());
        assertEquals(trainings, trainer.getTrainings());
    }

    @Test
    public void testDefaultConstructor() {
        Trainer trainer = new Trainer();

        assertNotNull(trainer);
        assertNull(trainer.getId()); // Since it's generated, it should be null
        assertNull(trainer.getTrainingType());
        assertNull(trainer.getUser());
        assertNull(trainer.getTrainings());
    }

    @Test
    public void testGettersAndSetters() {
        Trainer trainer = new Trainer();
        Long id = 1L;
        TrainingType trainingType = new TrainingType("Test Type");
        User user = new User(); // You need to create a valid User object
        List<Training> trainings = new ArrayList<>(); // You need to create valid Training objects

        trainer.setId(id);
        trainer.setTrainingType(trainingType);
        trainer.setUser(user);
        trainer.setTrainings(trainings);

        assertEquals(id, trainer.getId());
        assertEquals(trainingType, trainer.getTrainingType());
        assertEquals(user, trainer.getUser());
        assertEquals(trainings, trainer.getTrainings());
    }

    @Test
    public void testToString() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        TrainingType trainingType = new TrainingType("Test Type");
        trainer.setTrainingType(trainingType);
        User user = new User(); // You need to create a valid User object
        trainer.setUser(user);
        List<Training> trainings = new ArrayList<>(); // You need to create valid Training objects
        trainer.setTrainings(trainings);

        String expectedToString = "Trainer{id=1, trainingType=" + trainingType +
                ", user=" + user + ", trainings=" + trainings + '}';
        assertEquals(expectedToString, trainer.toString());
    }
}

