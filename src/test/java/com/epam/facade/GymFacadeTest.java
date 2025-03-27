package com.epam.facade;

import com.epam.service.TraineeService;
import com.epam.service.TrainerService;

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
    private TrainerService trainerService;

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

    // Trainee tests    
    @Test
    void createTrainee_ShouldCallServiceAndLogSuccess() {
        gymFacade.createTrainee("John", "Doe", "2000-01-01", "john@example.com");

        verify(traineeService).create("John", "Doe", "2000-01-01", "john@example.com");
        verify(logger).info("Trainee created successfully");
    }

    @Test
    void updateTrainee_ShouldCallServiceAndLogSuccess() {
        gymFacade.updateTrainee("john.doe", "2000-01-01", "123 Main St");

        verify(traineeService).update("john.doe", "2000-01-01", "123 Main St");
        verify(logger).info("Trainee updated successfully");
    }

    @Test
    void deleteTrainee_ShouldCallServiceAndLogSuccess() {
        gymFacade.deleteTrainee("john.doe");

        verify(traineeService).delete("john.doe");
        verify(logger).info("Trainee deleted successfully");
    }

    @Test
    void findTraineeByUsername_WhenExists_ShouldLogSuccess() {
        gymFacade.findTraineeByUsername("john.doe");

        verify(traineeService).findByUsername("john.doe");
        verify(logger).info("Trainee found successfully");
    }

    @Test
    void findTraineeByUsername_WhenNotFound_ShouldLogError() {
        doThrow(new IllegalArgumentException()).when(traineeService).findByUsername("invalid");

        gymFacade.findTraineeByUsername("invalid");

        verify(traineeService).findByUsername("invalid");
        verify(logger).error("Trainee not found");
    }

    // Trainer tests
    @Test
    void createTrainer_ShouldCallServiceAndLogSuccess() {
        gymFacade.createTrainer("John", "Doe", "stretching");

        verify(trainerService).create("John", "Doe", "stretching");
        verify(logger).info("Trainer created successfully");
    }

    @Test
    void updateTrainer_ShouldCallServiceAndLogSuccess() {
        gymFacade.updateTrainer("john.doe", "stretching");

        verify(trainerService).update("john.doe", "stretching");
        verify(logger).info("Trainer updated successfully");
    }

    @Test
    void findTrainerByUsername_WhenExists_ShouldLogSuccess() {
        gymFacade.findTrainerByUsername("john.doe");

        verify(trainerService).findByUsername("john.doe");
        verify(logger).info("Trainer found successfully");
    }

    @Test
    void findTrainerByUsername_WhenNotFound_ShouldLogError() {
        doThrow(new IllegalArgumentException()).when(trainerService).findByUsername("invalid");

        gymFacade.findTrainerByUsername("invalid");

        verify(trainerService).findByUsername("invalid");
        verify(logger).error("Trainer not found");
    }
}