package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.epam.dao.TraineeDAO;
import com.epam.dao.TrainerDAO;
import com.epam.dao.TrainingDAO;
import com.epam.model.Training;
import com.epam.model.TrainingType;

class TrainingServiceTest {

    @Mock
    private TrainingDAO trainingDAO;

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private TrainerDAO trainerDAO;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainingSuccessfully() {
        String traineeName = "trainee1";
        String trainerName = "trainer1";
        TrainingType trainingType = TrainingType.YOGA;
        String name = "Morning Yoga";
        String date = "2023-10-01";
        String duration = "1h";

        when(traineeDAO.exists(traineeName)).thenReturn(true);
        when(trainerDAO.exists(trainerName)).thenReturn(true);

        Training training = new Training(traineeName, trainerName, trainingType, name, date, duration);
        doReturn(training).when(trainingDAO).save(any(Training.class));

        Training createdTraining = trainingService.create(traineeName, trainerName, trainingType, name, date, duration);

        assertNotNull(createdTraining);
        assertEquals(traineeName, createdTraining.getTraineeName());
        assertEquals(trainerName, createdTraining.getTrainerName());
        assertEquals(trainingType, createdTraining.getTrainingType());
        assertEquals(name, createdTraining.getName());
        assertEquals(date, createdTraining.getDate());
        assertEquals(duration, createdTraining.getDuration());
        verify(traineeDAO).exists(traineeName);
        verify(trainerDAO).exists(trainerName);
        verify(trainingDAO).save(any(Training.class));
    }

    @Test
    void testCreateTrainingThrowsExceptionWhenTraineeNotFound() {
        String traineeName = "nonexistentTrainee";
        String trainerName = "trainer1";
        TrainingType trainingType = TrainingType.YOGA;
        String name = "Morning Yoga";
        String date = "2023-10-01";
        String duration = "1h";

        when(traineeDAO.exists(traineeName)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            trainingService.create(traineeName, trainerName, trainingType, name, date, duration)
        );

        assertEquals("Trainee with username " + traineeName + " not found", exception.getMessage());
        verify(traineeDAO).exists(traineeName);
        verifyNoInteractions(trainerDAO);
        verifyNoInteractions(trainingDAO);
    }

    @Test
    void testCreateTrainingThrowsExceptionWhenTrainerNotFound() {
        String traineeName = "trainee1";
        String trainerName = "nonexistentTrainer";
        TrainingType trainingType = TrainingType.YOGA;
        String name = "Morning Yoga";
        String date = "2023-10-01";
        String duration = "1h";

        when(traineeDAO.exists(traineeName)).thenReturn(true);
        when(trainerDAO.exists(trainerName)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            trainingService.create(traineeName, trainerName, trainingType, name, date, duration)
        );

        assertEquals("Trainer with username " + trainerName + " not found", exception.getMessage());
        verify(traineeDAO).exists(traineeName);
        verify(trainerDAO).exists(trainerName);
        verifyNoInteractions(trainingDAO);
    }

    @Test
    void testFindByIdSuccessfully() {
        String trainingId = "training1";
        Training training = new Training("trainee1", "trainer1", TrainingType.YOGA, "Morning Yoga", "2023-10-01", "1h");

        when(trainingDAO.exists(trainingId)).thenReturn(true);
        when(trainingDAO.findById(trainingId)).thenReturn(training);

        Optional<Training> foundTraining = trainingService.findById(trainingId);

        assertTrue(foundTraining.isPresent());
        assertEquals(training, foundTraining.get());
        verify(trainingDAO).exists(trainingId);
        verify(trainingDAO).findById(trainingId);
    }

    @Test
    void testFindByIdThrowsExceptionWhenTrainingNotFound() {
        String trainingId = "nonexistentTraining";

        when(trainingDAO.exists(trainingId)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            trainingService.findById(trainingId)
        );

        assertEquals("Training with id " + trainingId + " not found", exception.getMessage());
        verify(trainingDAO).exists(trainingId);
        verifyNoMoreInteractions(trainingDAO);
    }
}