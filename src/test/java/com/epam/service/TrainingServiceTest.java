package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.epam.dao.impl.CreateReadDaoImpl;
import com.epam.model.Trainee;
import com.epam.model.Trainer;
import com.epam.model.Training;
import com.epam.model.TrainingType;

class TrainingServiceTest {

    @Mock
    private CreateReadDaoImpl<Training, String> trainingDAO;

    @Mock
    private CreateReadDaoImpl<Trainee, String> traineeDAO;

    @Mock
    private CreateReadDaoImpl<Trainer, String> trainerDAO;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTraining() {
        String traineeName = "trainee1";
        String trainerName = "trainer1";
        TrainingType trainingType = TrainingType.YOGA;
        String name = "Java Training";
        String date = "2023-10-01";
        String duration = "2 hours";

        when(traineeDAO.exists(traineeName)).thenReturn(true);
        when(trainerDAO.exists(trainerName)).thenReturn(true);

        Training training = new Training.Builder()
            .traineeName(traineeName)
            .trainerName(trainerName)
            .trainingType(trainingType)
            .name(name)
            .date(date)
            .duration(duration)
            .build();

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
    void testCreateTrainingTraineeNotFound() {
        String traineeName = "trainee1";
        String trainerName = "trainer1";
        TrainingType trainingType = TrainingType.YOGA;
        String name = "Java Training";
        String date = "2023-10-01";
        String duration = "2 hours";

        when(traineeDAO.exists(traineeName)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingService.create(traineeName, trainerName, trainingType, name, date, duration);
        });

        assertEquals("Trainee with username " + traineeName + " not found", exception.getMessage());
        verify(traineeDAO).exists(traineeName);
        verify(trainerDAO, never()).exists(anyString());
        verify(trainingDAO, never()).save(any(Training.class));
    }

    @Test
    void testCreateTrainingTrainerNotFound() {
        String traineeName = "trainee1";
        String trainerName = "trainer1";
        TrainingType trainingType = TrainingType.YOGA;
        String name = "Java Training";
        String date = "2023-10-01";
        String duration = "2 hours";

        when(traineeDAO.exists(traineeName)).thenReturn(true);
        when(trainerDAO.exists(trainerName)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingService.create(traineeName, trainerName, trainingType, name, date, duration);
        });

        assertEquals("Trainer with username " + trainerName + " not found", exception.getMessage());
        verify(traineeDAO).exists(traineeName);
        verify(trainerDAO).exists(trainerName);
        verify(trainingDAO, never()).save(any(Training.class));
    }

    @Test
    void testFindById() {
        String id = "training1";
        Training training = new Training.Builder()
            .traineeName("trainee1")
            .trainerName("trainer1")
            .trainingType(TrainingType.YOGA)
            .name("Java Training")
            .date("2023-10-01")
            .duration("2 hours")
            .build();

        when(trainingDAO.exists(id)).thenReturn(true);
        when(trainingDAO.findByUsername(id)).thenReturn(training);

        Optional<Training> foundTraining = trainingService.findById(id);

        assertTrue(foundTraining.isPresent());
        assertEquals(training, foundTraining.get());

        verify(trainingDAO).exists(id);
        verify(trainingDAO).findByUsername(id);
    }

    @Test
    void testFindByIdNotFound() {
        String id = "training1";

        when(trainingDAO.exists(id)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingService.findById(id);
        });

        assertEquals("Training with id " + id + " not found", exception.getMessage());
        verify(trainingDAO).exists(id);
        verify(trainingDAO, never()).findByUsername(anyString());
    }
}