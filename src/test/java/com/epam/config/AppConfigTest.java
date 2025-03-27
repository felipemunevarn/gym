package com.epam.config;

import com.epam.model.Trainee;
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

    private static Path tempFile;

    @BeforeAll
    static void setup() throws IOException {
        tempFile = Files.createTempFile("test-trainee", ".txt");
        Files.write(tempFile, List.of(
            "john.doe,John,Doe,pass123,01-01-2000,elm street 123",
            "jane.smith,Jane,Smith,pass456,02-02-2000,742 evergreen terrace"
        ));
    }

    @AfterAll
    static void cleanup() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("storage.trainee.file", () -> tempFile.toString());
    }

    @Autowired
    private Map<String, Trainee> traineeStorage;

    @Test
    void testTraineeStorageInitialization() {
        // Check map size
        assertEquals(2, traineeStorage.size());

        // Verify entries exist
        assertTrue(traineeStorage.containsKey("john.doe"));
        assertTrue(traineeStorage.containsKey("jane.smith"));

        // Check first trainee details
        Trainee john = traineeStorage.get("john.doe");
        assertNotNull(john);
        assertEquals("John", john.getFirstName());
        assertEquals("Doe", john.getLastName());
        // assertEquals("john@example.com", john.getEmail());
        assertEquals("pass123", john.getPassword());
        // assertEquals("101", john.getId());

        // Check second trainee details
        Trainee jane = traineeStorage.get("jane.smith");
        assertNotNull(jane);
        assertEquals("Jane", jane.getFirstName());
        assertEquals("Smith", jane.getLastName());
        // assertEquals("jane@example.com", jane.getEmail());
        assertEquals("pass456", jane.getPassword());
        // assertEquals("102", jane.getId());
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