package com.epam.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    void testDefaultConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void testParameterizedConstructor() {
        User user = new User("John", "Doe", "john.doe", "password123", true);
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertTrue(user.getActive());
    }

    @Test
    void testGettersAndSetters() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setUsername("jane.smith");
        user.setPassword("securePassword");
        user.setActive(false);

        assertEquals(1L, user.getId());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("jane.smith", user.getUsername());
        assertEquals("securePassword", user.getPassword());
        assertFalse(user.getActive());
    }

    @Test
    void testToString() {
        User user = new User("Alice", "Wonderland", "alice", "pass123", true);
        String expectedString = "User{id=null, firstName='Alice', lastName='Wonderland', " +
                "username='alice', password='pass123', active=true}";
        assertEquals(expectedString, user.toString());
    }
}
