package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.epam.dao.TrainerDAO;
import com.epam.model.Trainer;

class TrainerServiceTest {

    @Mock
    private TrainerDAO trainerDAO;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        String firstName = "John";
        String lastName = "Doe";
        String specialization = "stretching";

        when(trainerDAO.exists(anyString())).thenReturn(false);

        Trainer createdTrainer = trainerService.create(firstName, lastName, specialization);
        
        assertNotNull(createdTrainer);
        assertEquals("John.Doe", createdTrainer.getUsername());
        assertEquals(firstName, createdTrainer.getFirstName());
        assertEquals(lastName, createdTrainer.getLastName());
        assertEquals(specialization, createdTrainer.getSpecialization());
        verify(trainerDAO).save(any(Trainer.class));
    }

    @Test
    void testUpdate() {
        String username = "john.doe";
        String newSpecialization = "yoga";

        Trainer existingTrainer = new Trainer(username, "John", "Doe", "password123", "stretching");

        when(trainerDAO.exists(username)).thenReturn(true);
        when(trainerDAO.findByUsername(username)).thenReturn(existingTrainer);

        Optional<Trainer> updatedTrainer = trainerService.update(username, newSpecialization);

        assertTrue(updatedTrainer.isPresent());
        assertEquals(newSpecialization, updatedTrainer.get().getSpecialization());
        verify(trainerDAO).findByUsername(username);
    }

    @Test
    void testUpdateThrowsExceptionWhenTrainerNotFound() {
        String username = "nonexistent";

        when(trainerDAO.exists(username)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> trainerService.update(username, "stretching"));
    }

    @Test
    void testFindByUsername() {
        String username = "john.doe";
        Trainer existingTrainer = new Trainer(username, "John", "Doe", "password123", "stretching");

        when(trainerDAO.exists(username)).thenReturn(true);
        when(trainerDAO.findByUsername(username)).thenReturn(existingTrainer);

        Optional<Trainer> foundTrainer = trainerService.findByUsername(username);

        assertTrue(foundTrainer.isPresent());
        assertEquals(existingTrainer, foundTrainer.get());
        verify(trainerDAO).findByUsername(username);
    }

    @Test
    void testFindByUsernameThrowsExceptionWhenTrainerNotFound() {
        String username = "nonexistent";

        when(trainerDAO.exists(username)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> trainerService.findByUsername(username));
    }
    @Test
    void testGenerateUsernameWhenNoConflict() {
        String firstName = "Jane";
        String lastName = "Smith";
        String expectedUsername = "Jane.Smith";
        
        when(trainerDAO.exists(expectedUsername)).thenReturn(false);
        
        String generatedUsername = trainerService.create(firstName, lastName, "stretching").getUsername();
        
        assertEquals(expectedUsername, generatedUsername);
        verify(trainerDAO).exists(expectedUsername);
    }
    
    @Test
    void testGenerateUsernameWhenConflictExists() {
        String firstName = "Jane";
        String lastName = "Smith";
        String baseUsername = "Jane.Smith";
        String expectedUsername = "Jane.Smith1";
        
        when(trainerDAO.exists(baseUsername)).thenReturn(true);
        when(trainerDAO.exists(expectedUsername)).thenReturn(false);
        
        String generatedUsername = trainerService.create(firstName, lastName, "stretching").getUsername();

        assertEquals(expectedUsername, generatedUsername);
        verify(trainerDAO).exists(baseUsername);
        verify(trainerDAO).exists(expectedUsername);
    }

    @Test
    void testGenerateUsernameWhenMultipleConflictsExist() {
        String firstName = "Jane";
        String lastName = "Smith";
        String baseUsername = "Jane.Smith";
        String conflictUsername1 = "Jane.Smith1";
        String conflictUsername2 = "Jane.Smith2";
        String expectedUsername = "Jane.Smith3";
        
        when(trainerDAO.exists(baseUsername)).thenReturn(true);
        when(trainerDAO.exists(conflictUsername1)).thenReturn(true);
        when(trainerDAO.exists(conflictUsername2)).thenReturn(true);
        when(trainerDAO.exists(expectedUsername)).thenReturn(false);
        
        String generatedUsername = trainerService.create(firstName, lastName, "stretching").getUsername();
        
        assertEquals(expectedUsername, generatedUsername);
        verify(trainerDAO).exists(baseUsername);
        verify(trainerDAO).exists(conflictUsername1);
        verify(trainerDAO).exists(conflictUsername2);
        verify(trainerDAO).exists(expectedUsername);
    }
}