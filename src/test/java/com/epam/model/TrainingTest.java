package com.epam.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TrainingTest {

    @Test
    void testConstructorAndGetters() {
        String traineeName = "Alice";
        String trainerName = "Bob";
        TrainingType.Type trainingType = TrainingType.Type.YOGA;
        String name = "Morning Yoga";
        String date = "2023-10-01";
        String duration = "1 hour";

        Training training = new Training(traineeName, trainerName, trainingType, name, date, duration);

        assertEquals(traineeName, training.getTraineeName());
        assertEquals(trainerName, training.getTrainerName());
        assertEquals(trainingType, training.getTrainingType());
        assertEquals(name, training.getName());
        assertEquals(date, training.getDate());
        assertEquals(duration, training.getDuration());
    }

    @Test
    void testSetters() {
        Training training = new Training("Alice", "Bob", TrainingType.Type.YOGA, "Morning Yoga", "2023-10-01", "1 hour");

        training.setTraineeName("Charlie");
        training.setTrainerName("David");
        training.setTrainingType(TrainingType.Type.PILATES);
        training.setName("Evening Pilates");
        training.setDate("2023-10-02");
        training.setDuration("2 hours");

        assertEquals("Charlie", training.getTraineeName());
        assertEquals("David", training.getTrainerName());
        assertEquals(TrainingType.Type.PILATES, training.getTrainingType());
        assertEquals("Evening Pilates", training.getName());
        assertEquals("2023-10-02", training.getDate());
        assertEquals("2 hours", training.getDuration());
    }

    @Test
    void testToString() {
        Training training = new Training("Alice", "Bob", TrainingType.Type.YOGA, "Morning Yoga", "2023-10-01", "1 hour");

        String expected = "Training [traineeName=Alice, trainerName=Bob, trainingType=YOGA, name=Morning Yoga, date=2023-10-01, duration=1 hour]";
        assertEquals(expected, training.toString());
    }
}