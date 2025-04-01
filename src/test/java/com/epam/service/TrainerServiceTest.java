package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.epam.dao.impl.CreateReadUpdateDaoImpl;
import com.epam.model.Trainer;
import com.epam.util.AccountGenerator;

class TrainerServiceTest {

    @Mock
    private CreateReadUpdateDaoImpl<Trainer, String> trainerDAO;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainer() {
        String firstName = "John";
        String lastName = "Doe";
        String specialization = "Java";
        String generatedUsername = "john.doe";
        String generatedPassword = "password123";

        mockStatic(AccountGenerator.class);
        when(AccountGenerator.generateUsername(eq(firstName), eq(lastName), any())).thenReturn(generatedUsername);
        when(AccountGenerator.generatePassword()).thenReturn(generatedPassword);

        Trainer trainer = new Trainer.Builder()
            .username(generatedUsername)
            .firstName(firstName)
            .lastName(lastName)
            .password(generatedPassword)
            .specialization(specialization)
            .build();

        doReturn(trainer).when(trainerDAO).save(any(Trainer.class));

        Trainer createdTrainer = trainerService.create(firstName, lastName, specialization);

        assertNotNull(createdTrainer);
        assertEquals(generatedUsername, createdTrainer.getUsername());
        assertEquals(firstName, createdTrainer.getFirstName());
        assertEquals(lastName, createdTrainer.getLastName());
        assertEquals(generatedPassword, createdTrainer.getPassword());
        assertEquals(specialization, createdTrainer.getSpecialization());

        verify(trainerDAO).save(any(Trainer.class));
    }

    @Test
    void testUpdateTrainer() {
        String username = "john.doe";
        String specialization = "Python";
        Trainer existingTrainer = new Trainer.Builder()
            .username(username)
            .specialization("Java")
            .build();

        when(trainerDAO.exists(username)).thenReturn(true);
        when(trainerDAO.findByUsername(username)).thenReturn(existingTrainer);

        Optional<Trainer> updatedTrainer = trainerService.update(username, specialization);

        assertTrue(updatedTrainer.isPresent());
        assertEquals(specialization, updatedTrainer.get().getSpecialization());

        verify(trainerDAO).exists(username);
        verify(trainerDAO).findByUsername(username);
        verify(trainerDAO).save(existingTrainer);
    }

    @Test
    void testUpdateTrainerNotFound() {
        String username = "john.doe";
        String specialization = "Python";

        when(trainerDAO.exists(username)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainerService.update(username, specialization);
        });

        assertEquals("Trainer with username " + username + " not found", exception.getMessage());
        verify(trainerDAO).exists(username);
        verify(trainerDAO, never()).findByUsername(anyString());
        verify(trainerDAO, never()).save(any(Trainer.class));
    }

    @Test
    void testFindByUsername() {
        String username = "john.doe";
        Trainer trainer = new Trainer.Builder().username(username).build();

        when(trainerDAO.exists(username)).thenReturn(true);
        when(trainerDAO.findByUsername(username)).thenReturn(trainer);

        Optional<Trainer> foundTrainer = trainerService.findByUsername(username);

        assertTrue(foundTrainer.isPresent());
        assertEquals(username, foundTrainer.get().getUsername());

        verify(trainerDAO).exists(username);
        verify(trainerDAO).findByUsername(username);
    }

    @Test
    void testFindByUsernameNotFound() {
        String username = "john.doe";

        when(trainerDAO.exists(username)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainerService.findByUsername(username);
        });

        assertEquals("Trainer with username " + username + " not found", exception.getMessage());
        verify(trainerDAO).exists(username);
        verify(trainerDAO, never()).findByUsername(anyString());
    }
}