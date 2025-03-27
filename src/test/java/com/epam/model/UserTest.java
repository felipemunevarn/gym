package com.epam.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User("john_doe", "John", "Doe", "password123");

        assertEquals("john_doe", user.getUsername());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testSetUsername() {
        User user = new User("john_doe", "John", "Doe", "password123");
        user.setUsername("jane_doe");

        assertEquals("jane_doe", user.getUsername());
    }

    @Test
    void testSetFirstName() {
        User user = new User("john_doe", "John", "Doe", "password123");
        user.setFirstName("Jane");

        assertEquals("Jane", user.getFirstName());
    }

    @Test
    void testSetLastName() {
        User user = new User("john_doe", "John", "Doe", "password123");
        user.setLastName("Smith");

        assertEquals("Smith", user.getLastName());
    }

    @Test
    void testSetPassword() {
        User user = new User("john_doe", "John", "Doe", "password123");
        user.setPassword("newpassword");

        assertEquals("newpassword", user.getPassword());
    }

    @Test
    void testSetActive() {
        User user = new User("john_doe", "John", "Doe", "password123");
        assertTrue(user.isActive());
        user.setActive(false);

        assertFalse(user.isActive());
    }
}
