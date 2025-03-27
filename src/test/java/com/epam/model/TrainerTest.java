package com.epam.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {

    @Test
    void testTrainerConstructorAndGetters() {
        Trainer trainer = new Trainer("john_doe", "John", "Doe", "password123", "stretching");

        assertEquals("john_doe", trainer.getUsername());
        assertEquals("John", trainer.getFirstName());
        assertEquals("Doe", trainer.getLastName());
        assertEquals("password123", trainer.getPassword());
        assertEquals("stretching", trainer.getSpecialization());
    }

    @Test
    void testSetSpecialization() {
        Trainer trainer = new Trainer("john_doe", "John", "Doe", "password123", "stretching");
        trainer.setSpecialization("muscle toning");

        assertEquals("muscle toning", trainer.getSpecialization());
    }

    @Test
    void testToString() {
        Trainer trainer = new Trainer("john_doe", "John", "Doe", "password123", "stretching");
        String expected = "Trainer [username=john_doe, firstName=John, lastName=Doe, password=password123, specialization=stretching]";

        assertEquals(expected, trainer.toString());
    }
}