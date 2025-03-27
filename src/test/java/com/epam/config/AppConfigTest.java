package com.epam.config;

import com.epam.model.Trainee;
import com.epam.model.Trainer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
public class AppConfigTest {

    private static Path tempFileTrainee;
    private static Path tempFileTrainer;

    @BeforeAll
    static void setup() throws IOException {
        tempFileTrainee = Files.createTempFile("test-trainee", ".txt");
        Files.write(tempFileTrainee, List.of(
            "john.doe,John,Doe,pass123,01-01-2000,elm street 123",
            "jane.smith,Jane,Smith,pass456,02-02-2000,742 evergreen terrace"
        ));
        
        tempFileTrainer = Files.createTempFile("test-trainer", ".txt");
        Files.write(tempFileTrainer, List.of(
            "john.doe,John,Doe,pass123,stretching",
            "jane.smith,Jane,Smith,pass456,yoga"
        ));
    }

    @AfterAll
    static void cleanup() throws IOException {
        Files.deleteIfExists(tempFileTrainee);
        Files.deleteIfExists(tempFileTrainer);
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("storage.trainee.file", () -> tempFileTrainee.toString());
        registry.add("storage.trainer.file", () -> tempFileTrainer.toString());
    }

    @Autowired
    private Map<String, Trainee> traineeStorage;
    @Autowired
    private Map<String, Trainer> trainerStorage;

    @Test
    void testTraineeStorageInitialization() {
        assertEquals(2, traineeStorage.size());
        assertEquals(2, trainerStorage.size());

        assertTrue(traineeStorage.containsKey("john.doe"));
        assertTrue(traineeStorage.containsKey("jane.smith"));
        assertTrue(trainerStorage.containsKey("john.doe"));
        assertTrue(trainerStorage.containsKey("jane.smith"));

        Trainee johnTe = traineeStorage.get("john.doe");
        assertNotNull(johnTe);
        assertEquals("John", johnTe.getFirstName());
        assertEquals("Doe", johnTe.getLastName());
        assertEquals("pass123", johnTe.getPassword());

        Trainer johnTr = trainerStorage.get("john.doe");
        assertNotNull(johnTr);
        assertEquals("John", johnTr.getFirstName());
        assertEquals("Doe", johnTr.getLastName());
        assertEquals("stretching", johnTr.getSpecialization());

        Trainee janeTe = traineeStorage.get("jane.smith");
        assertNotNull(janeTe);
        assertEquals("Jane", janeTe.getFirstName());
        assertEquals("Smith", janeTe.getLastName());
        assertEquals("pass456", janeTe.getPassword());
        
        Trainer janeTr = trainerStorage.get("jane.smith");
        assertNotNull(janeTr);
        assertEquals("Jane", janeTr.getFirstName());
        assertEquals("Smith", janeTr.getLastName());
        assertEquals("pass456", janeTr.getPassword());
    }

    @Test
    void testLoadFromFileThrowsExceptionForInvalidFile() {
        // Arrange: Create a configuration with a non-existent file
        System.setProperty("storage.trainee.file", "non_existent_file.txt");

        // Act & Assert: Verify Spring context initialization fails
        Exception exception = assertThrows(
            Exception.class,
            () -> new AnnotationConfigApplicationContext(AppConfig.class)
        );

        // Verify root cause
        Throwable rootCause = exception.getCause();
        assertInstanceOf(RuntimeException.class, rootCause);
        assertEquals(
            "Error reading file: non_existent_file.txt",
            rootCause.getMessage(),
            "Exception message should match file name"
        );
    }
}