package com.epam.facade;

import com.epam.service.TraineeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class GymFacadeTest {

    @Mock
    private TraineeService traineeService;

    @Mock
    private Logger logger; // Mock the logger

    @InjectMocks
    private GymFacade gymFacade;

    @BeforeEach
    void injectMockLogger() throws NoSuchFieldException, IllegalAccessException {
        // Use reflection to inject the mocked logger into GymFacade
        Field loggerField = GymFacade.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(gymFacade, logger);
    }

    @Test
    void createTrainee_ShouldCallServiceAndLogSuccess() {
        // Act
        gymFacade.createTrainee("John", "Doe", "2000-01-01", "john@example.com");

        // Assert
        verify(traineeService).create("John", "Doe", "2000-01-01", "john@example.com");
        verify(logger).info("Trainee created successfully");
    }

    @Test
    void updateTrainee_ShouldCallServiceAndLogSuccess() {
        // Act
        gymFacade.updateTrainee("john.doe", "2000-01-01", "123 Main St");

        // Assert
        verify(traineeService).update("john.doe", "2000-01-01", "123 Main St");
        verify(logger).info("Trainee updated successfully");
    }

    @Test
    void deleteTrainee_ShouldCallServiceAndLogSuccess() {
        // Act
        gymFacade.deleteTrainee("john.doe");

        // Assert
        verify(traineeService).delete("john.doe");
        verify(logger).info("Trainee deleted successfully");
    }

    @Test
    void findTraineeByUsername_WhenExists_ShouldLogSuccess() {
        // Act
        gymFacade.findTraineeByUsername("john.doe");

        // Assert
        verify(traineeService).findByUsername("john.doe");
        verify(logger).info("Trainee found successfully");
    }

    @Test
    void findTraineeByUsername_WhenNotFound_ShouldLogError() {
        // Arrange
        doThrow(new IllegalArgumentException()).when(traineeService).findByUsername("invalid");

        // Act
        gymFacade.findTraineeByUsername("invalid");

        // Assert
        verify(traineeService).findByUsername("invalid");
        verify(logger).error("Trainee not found");
    }
}