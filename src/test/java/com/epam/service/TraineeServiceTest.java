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
import com.epam.model.Trainee;

class TraineeServiceTest {

    @Mock
    private TraineeDAO traineeDAO;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        String firstName = "John";
        String lastName = "Doe";
        String dateOfBirth = "1990-01-01";
        String address = "123 Main St";

        when(traineeDAO.exists(anyString())).thenReturn(false);

        Trainee createdTrainee = traineeService.create(firstName, lastName, dateOfBirth, address);
        
        assertNotNull(createdTrainee);
        assertEquals("John.Doe", createdTrainee.getUsername());
        assertEquals(firstName, createdTrainee.getFirstName());
        assertEquals(lastName, createdTrainee.getLastName());
        assertEquals(dateOfBirth, createdTrainee.getDateOfBirth());
        assertEquals(address, createdTrainee.getAddress());
        verify(traineeDAO).save(any(Trainee.class));
    }

    @Test
    void testUpdate() {
        String username = "john.doe";
        String newDateOfBirth = "1991-02-02";
        String newAddress = "456 Elm St";

        Trainee existingTrainee = new Trainee(username, "John", "Doe", "password123", "1990-01-01", "123 Main St");

        when(traineeDAO.exists(username)).thenReturn(true);
        when(traineeDAO.findByUsername(username)).thenReturn(existingTrainee);

        Optional<Trainee> updatedTrainee = traineeService.update(username, newDateOfBirth, newAddress);

        assertTrue(updatedTrainee.isPresent());
        assertEquals(newDateOfBirth, updatedTrainee.get().getDateOfBirth());
        assertEquals(newAddress, updatedTrainee.get().getAddress());
        verify(traineeDAO).findByUsername(username);
    }

    @Test
    void testUpdateThrowsExceptionWhenTraineeNotFound() {
        String username = "nonexistent";

        when(traineeDAO.exists(username)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> traineeService.update(username, "1991-02-02", "456 Elm St"));
    }

    @Test
    void testDelete() {
        String username = "john.doe";

        when(traineeDAO.exists(username)).thenReturn(true);

        assertDoesNotThrow(() -> traineeService.delete(username));
        verify(traineeDAO).delete(username);
    }

    @Test
    void testDeleteThrowsExceptionWhenTraineeNotFound() {
        String username = "nonexistent";

        when(traineeDAO.exists(username)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> traineeService.delete(username));
    }

    @Test
    void testFindByUsername() {
        String username = "john.doe";
        Trainee existingTrainee = new Trainee(username, "John", "Doe", "password123", "1990-01-01", "123 Main St");

        when(traineeDAO.exists(username)).thenReturn(true);
        when(traineeDAO.findByUsername(username)).thenReturn(existingTrainee);

        Optional<Trainee> foundTrainee = traineeService.findByUsername(username);

        assertTrue(foundTrainee.isPresent());
        assertEquals(existingTrainee, foundTrainee.get());
        verify(traineeDAO).findByUsername(username);
    }

    @Test
    void testFindByUsernameThrowsExceptionWhenTraineeNotFound() {
        String username = "nonexistent";

        when(traineeDAO.exists(username)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> traineeService.findByUsername(username));
    }
    @Test
    void testGenerateUsernameWhenNoConflict() {
        String firstName = "Jane";
        String lastName = "Smith";
        String expectedUsername = "Jane.Smith";
        
        when(traineeDAO.exists(expectedUsername)).thenReturn(false);
        
        String generatedUsername = traineeService.create(firstName, lastName, "1992-03-03", "789 Pine St").getUsername();
        
        assertEquals(expectedUsername, generatedUsername);
        verify(traineeDAO).exists(expectedUsername);
    }
    
    @Test
    void testGenerateUsernameWhenConflictExists() {
        String firstName = "Jane";
        String lastName = "Smith";
        String baseUsername = "Jane.Smith";
        String expectedUsername = "Jane.Smith1";
        
        when(traineeDAO.exists(baseUsername)).thenReturn(true);
        when(traineeDAO.exists(expectedUsername)).thenReturn(false);
        
        String generatedUsername = traineeService.create(firstName, lastName, "1992-03-03", "789 Pine St").getUsername();

        assertEquals(expectedUsername, generatedUsername);
        verify(traineeDAO).exists(baseUsername);
        verify(traineeDAO).exists(expectedUsername);
    }

    @Test
    void testGenerateUsernameWhenMultipleConflictsExist() {
        String firstName = "Jane";
        String lastName = "Smith";
        String baseUsername = "Jane.Smith";
        String conflictUsername1 = "Jane.Smith1";
        String conflictUsername2 = "Jane.Smith2";
        String expectedUsername = "Jane.Smith3";
        
        when(traineeDAO.exists(baseUsername)).thenReturn(true);
        when(traineeDAO.exists(conflictUsername1)).thenReturn(true);
        when(traineeDAO.exists(conflictUsername2)).thenReturn(true);
        when(traineeDAO.exists(expectedUsername)).thenReturn(false);
        
        String generatedUsername = traineeService.create(firstName, lastName, "1992-03-03", "789 Pine St").getUsername();
        
        assertEquals(expectedUsername, generatedUsername);
        verify(traineeDAO).exists(baseUsername);
        verify(traineeDAO).exists(conflictUsername1);
        verify(traineeDAO).exists(conflictUsername2);
        verify(traineeDAO).exists(expectedUsername);
    }
}