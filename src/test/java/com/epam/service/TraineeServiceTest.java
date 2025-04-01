package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.epam.dao.impl.CreateReadUpdateDeleteDaoImpl;
import com.epam.model.Trainee;
import com.epam.util.AccountGenerator;

class TraineeServiceTest {

    @Mock
    private CreateReadUpdateDeleteDaoImpl<Trainee, String> traineeDAO;

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
        String username = "john.doe";
        String password = "password123";

        mockStatic(AccountGenerator.class);
        when(AccountGenerator.generateUsername(eq(firstName), eq(lastName), any())).thenReturn(username);
        when(AccountGenerator.generatePassword()).thenReturn(password);

        Trainee trainee = new Trainee.Builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .dateOfBirth(dateOfBirth)
                .address(address)
                .build();

        doNothing().when(traineeDAO).save(trainee);

        Trainee createdTrainee = traineeService.create(firstName, lastName, dateOfBirth, address);

        assertEquals(username, createdTrainee.getUsername());
        assertEquals(firstName, createdTrainee.getFirstName());
        assertEquals(lastName, createdTrainee.getLastName());
        assertEquals(password, createdTrainee.getPassword());
        assertEquals(dateOfBirth, createdTrainee.getDateOfBirth());
        assertEquals(address, createdTrainee.getAddress());
    }

    @Test
    void testUpdate() {
        String username = "john.doe";
        String dateOfBirth = "1991-01-01";
        String address = "456 Elm St";

        Trainee existingTrainee = new Trainee.Builder()
                .username(username)
                .dateOfBirth("1990-01-01")
                .address("123 Main St")
                .build();

        when(traineeDAO.exists(username)).thenReturn(true);
        when(traineeDAO.findByUsername(username)).thenReturn(existingTrainee);

        Trainee updatedTrainee = new Trainee.Builder()
                .username(username)
                .dateOfBirth(dateOfBirth)
                .address(address)
                .build();

        doNothing().when(traineeDAO).save(updatedTrainee);

        Optional<Trainee> result = traineeService.update(updatedTrainee);

        assertTrue(result.isPresent());
        assertEquals(dateOfBirth, result.get().getDateOfBirth());
        assertEquals(address, result.get().getAddress());
    }

    @Test
    void testDelete() {
        String username = "john.doe";

        when(traineeDAO.exists(username)).thenReturn(true);
        doNothing().when(traineeDAO).delete(username);

        assertDoesNotThrow(() -> traineeService.delete(username));
        verify(traineeDAO).delete(username);
    }

    @Test
    void testFindByUsername() {
        String username = "john.doe";

        Trainee trainee = new Trainee.Builder()
                .username(username)
                .build();

        when(traineeDAO.exists(username)).thenReturn(true);
        when(traineeDAO.findByUsername(username)).thenReturn(trainee);

        Optional<Trainee> result = traineeService.findByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        verify(traineeDAO).findByUsername(username);
    }

    @Test
    void testFindByUsernameThrowsExceptionWhenNotFound() {
        String username = "john.doe";

        when(traineeDAO.exists(username)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            traineeService.findByUsername(username);
        });

        assertEquals("Trainee with username " + username + " not found", exception.getMessage());
    }
}