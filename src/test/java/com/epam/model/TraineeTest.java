package com.epam.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TraineeTest {

    @Test
    void testTraineeConstructorAndGetters() {
        Trainee trainee = new Trainee("john_doe", "John", "Doe", "password123", "1990-01-01", "123 Main St");

        assertEquals("john_doe", trainee.getUsername());
        assertEquals("John", trainee.getFirstName());
        assertEquals("Doe", trainee.getLastName());
        assertEquals("password123", trainee.getPassword());
        assertEquals("1990-01-01", trainee.getDateOfBirth());
        assertEquals("123 Main St", trainee.getAddress());
    }

    @Test
    void testSetDateOfBirth() {
        Trainee trainee = new Trainee("john_doe", "John", "Doe", "password123", "1990-01-01", "123 Main St");
        trainee.setDateOfBirth("2000-12-31");

        assertEquals("2000-12-31", trainee.getDateOfBirth());
    }

    @Test
    void testSetAddress() {
        Trainee trainee = new Trainee("john_doe", "John", "Doe", "password123", "1990-01-01", "123 Main St");
        trainee.setAddress("456 Elm St");

        assertEquals("456 Elm St", trainee.getAddress());
    }

    @Test
    void testToString() {
        Trainee trainee = new Trainee("john_doe", "John", "Doe", "password123", "1990-01-01", "123 Main St");
        String expected = "Trainee [username=john_doe, firstName=John, lastName=Doe, password=password123, dateOfBirth=1990-01-01, address=123 Main St]";

        assertEquals(expected, trainee.toString());
    }
}