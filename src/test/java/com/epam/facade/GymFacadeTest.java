package com.epam.facade;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import com.epam.model.Trainee;
import com.epam.model.TrainingType;
import com.epam.service.TraineeService;
import com.epam.service.TrainerService;
import com.epam.service.TrainingService;

@ExtendWith(MockitoExtension.class)
class GymFacadeTest {

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainingService trainingService;

    @Mock
    private Logger logger;

    @InjectMocks
    private GymFacade gymFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void injectMockLogger() throws NoSuchFieldException, IllegalAccessException {
        // Reflection to inject the mocked logger into GymFacade
        Field loggerField = GymFacade.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(gymFacade, logger);
    }

    @Test
    void testCreateTrainee() {
        String firstName = "John";
        String lastName = "Doe";
        String dateOfBirth = "1990-01-01";
        String address = "123 Main St";

        gymFacade.createTrainee(firstName, lastName, dateOfBirth, address);

        verify(traineeService).create(firstName, lastName, dateOfBirth, address);
        verify(logger).info("Trainee created successfully");
    }

    @Test
    void testUpdateTrainee() {
        String firstName = "John";
        String lastName = "Doe";
        String dateOfBirth = "1990-01-01";
        String address = "123 Main St";

        gymFacade.createTrainee(firstName, lastName, dateOfBirth, address);

        Trainee trainee = new Trainee.Builder()
                .username("John.Doe")
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth("1990-01-01")
                .address("456 Elm St")
                .build();

        when(traineeService.update(trainee)).thenReturn(Optional.of(trainee));

        // String username = "john.doe";
        // String dateOfBirth = "1990-01-01";
        // String address = "456 Elm St";

        gymFacade.updateTrainee(trainee);

        verify(traineeService).update(trainee);
        verify(logger).info("Trainee updated successfully");
    }

    @Test
    void testDeleteTrainee() {
        String username = "john.doe";

        gymFacade.deleteTrainee(username);

        verify(traineeService).delete(username);
        verify(logger).info("Trainee deleted successfully");
    }

    @Test
    void testFindTraineeByUsername() {
        String username = "john.doe";

        doNothing().when(traineeService).findByUsername(username);

        gymFacade.findTraineeByUsername(username);

        verify(traineeService).findByUsername(username);
        verify(logger).info("Trainee: " + username + " found successfully");
    }

    @Test
    void testFindTraineeByUsernameNotFound() {
        String username = "john.doe";

        doThrow(new IllegalArgumentException("Trainee not found")).when(traineeService).findByUsername(username);

        gymFacade.findTraineeByUsername(username);

        verify(traineeService).findByUsername(username);
        verify(logger).error("Trainee: " + username + " not found");
    }

    @Test
    void testCreateTrainer() {
        String firstName = "Jane";
        String lastName = "Smith";
        String specialization = "Yoga";

        gymFacade.createTrainer(firstName, lastName, specialization);

        verify(trainerService).create(firstName, lastName, specialization);
        verify(logger).info("Trainer created successfully");
    }

    @Test
    void testUpdateTrainer() {
        String username = "jane.smith";
        String specialization = "Pilates";

        gymFacade.updateTrainer(username, specialization);

        verify(trainerService).update(username, specialization);
        verify(logger).info("Trainer: " + username + " updated successfully");
    }

    @Test
    void testFindTrainerByUsername() {
        String username = "jane.smith";

        doNothing().when(trainerService).findByUsername(username);

        gymFacade.findTrainerByUsername(username);

        verify(trainerService).findByUsername(username);
        verify(logger).info("Trainer: " + username + " found successfully");
    }

    @Test
    void testFindTrainerByUsernameNotFound() {
        String username = "jane.smith";

        doThrow(new IllegalArgumentException("Trainer not found")).when(trainerService).findByUsername(username);

        gymFacade.findTrainerByUsername(username);

        verify(trainerService).findByUsername(username);
        verify(logger).error("Trainer: " + username + " not found");
    }

    @Test
    void testCreateTraining() {
        String traineeName = "John Doe";
        String trainerName = "Jane Smith";
        TrainingType trainingType = TrainingType.YOGA;
        String name = "Morning Yoga";
        String date = "2023-10-01";
        String duration = "1h";

        gymFacade.createTraining(traineeName, trainerName, trainingType, name, date, duration);

        verify(trainingService).create(traineeName, trainerName, trainingType, name, date, duration);
        verify(logger).info("Training created successfully");
    }

    @Test
    void testFindTrainingById() {
        String id = "training123";

        doNothing().when(trainingService).findById(id);

        gymFacade.findTrainingById(id);

        verify(trainingService).findById(id);
        verify(logger).info("Training: " + id + " found successfully");
    }

    @Test
    void testFindTrainingByIdNotFound() {
        String id = "training123";

        doThrow(new IllegalArgumentException("Training not found")).when(trainingService).findById(id);

        gymFacade.findTrainingById(id);

        verify(trainingService).findById(id);
        verify(logger).error("Training: " + id + " not found");
    }
}