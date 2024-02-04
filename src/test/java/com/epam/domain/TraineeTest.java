package com.epam.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TraineeTest {

    @Test
    public void testParameterizedConstructor() {
        Date dateOfBirth = new Date();
        String address = "Test Address";
        User user = new User();
        List<Training> trainings = new ArrayList<>();

        Trainee trainee = new Trainee(dateOfBirth, address, user, trainings);

        assertNotNull(trainee);
        assertEquals(dateOfBirth, trainee.getDateOfBirth());
        assertEquals(address, trainee.getAddress());
        assertEquals(user, trainee.getUser());
        assertEquals(trainings, trainee.getTrainings());
    }

    @Test
    public void testDefaultConstructor() {
        Trainee trainee = new Trainee();

        assertNotNull(trainee);
        assertNull(trainee.getId());
        assertNull(trainee.getDateOfBirth());
        assertNull(trainee.getAddress());
        assertNull(trainee.getUser());
        assertNull(trainee.getTrainings());
    }

    @Test
    public void testGettersAndSetters() {
        Trainee trainee = new Trainee();
        Long id = 1L;
        Date dateOfBirth = new Date();
        String address = "Test Address";
        User user = new User();
        List<Training> trainings = new ArrayList<>();

        trainee.setId(id);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        trainee.setUser(user);
        trainee.setTrainings(trainings);

        assertEquals(id, trainee.getId());
        assertEquals(dateOfBirth, trainee.getDateOfBirth());
        assertEquals(address, trainee.getAddress());
        assertEquals(user, trainee.getUser());
        assertEquals(trainings, trainee.getTrainings());
    }

    @Test
    public void testToString() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setDateOfBirth(new Date());
        trainee.setAddress("Test Address");
        User user = new User();
        trainee.setUser(user);
        List<Training> trainings = new ArrayList<>();
        trainee.setTrainings(trainings);

        String expectedToString = "Trainee{id=1, dateOfBirth=" + trainee.getDateOfBirth() +
                ", address='Test Address', user=" + user + ", trainings=" + trainings + '}';
        assertEquals(expectedToString, trainee.toString());
    }
}

