package com.epam.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TraineeTest {

    @Test
    void testTraineeBuilderCreatesTraineeSuccessfully() {
        Trainee trainee = new Trainee.Builder()
                .username("trainee1")
                .firstName("John")
                .lastName("Doe")
                .password("password123")
                .dateOfBirth("1990-01-01")
                .address("123 Main St")
                .build();

        assertNotNull(trainee);
        assertEquals("trainee1", trainee.getUsername());
        assertEquals("John", trainee.getFirstName());
        assertEquals("Doe", trainee.getLastName());
        assertEquals("password123", trainee.getPassword());
        assertEquals("1990-01-01", trainee.getDateOfBirth());
        assertEquals("123 Main St", trainee.getAddress());
    }

    @Test
    void testGetIdReturnsUsername() {
        Trainee trainee = new Trainee.Builder()
                .username("trainee1")
                .build();

        assertEquals("trainee1", trainee.getId());
    }

    @Test
    void testToStringReturnsCorrectStringRepresentation() {
        Trainee trainee = new Trainee.Builder()
                .username("trainee1")
                .firstName("John")
                .lastName("Doe")
                .password("password123")
                .dateOfBirth("1990-01-01")
                .address("123 Main St")
                .build();

        String expected = "Trainee [username=trainee1, firstName=John, lastName=Doe, password=password123, dateOfBirth=1990-01-01, address=123 Main St]";
        assertEquals(expected, trainee.toString());
    }

    @Test
    void testBuilderAllowsPartialBuild() {
        Trainee trainee = new Trainee.Builder()
                .username("trainee1")
                .firstName("John")
                .build();

        assertNotNull(trainee);
        assertEquals("trainee1", trainee.getUsername());
        assertEquals("John", trainee.getFirstName());
        assertNull(trainee.getLastName());
        assertNull(trainee.getPassword());
        assertNull(trainee.getDateOfBirth());
        assertNull(trainee.getAddress());
    }
}