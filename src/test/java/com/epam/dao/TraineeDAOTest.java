package com.epam.dao;

import com.epam.model.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class TraineeDAOTest {

    private TraineeDAO traineeDAO;
    private Map<String, Trainee> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        traineeDAO = new TraineeDAO(storage);
    }

    @Test
    void testSave() {
        Trainee trainee = new Trainee("john_doe", "John", "Doe", "password123", "01-01-2000", "elm street 123");
        traineeDAO.save(trainee);
        
        assertTrue(storage.containsKey("john_doe"));
        assertEquals(trainee, storage.get("john_doe"));
    }
    
    @Test
    void testFindByUsername() {
        Trainee trainee = new Trainee("john_doe", "John", "Doe", "password123", "01-01-2000", "elm street 123");
        storage.put("john_doe", trainee);
        
        Trainee result = traineeDAO.findByUsername("john_doe");
        assertNotNull(result);
        assertEquals(trainee, result);
    }
    
    @Test
    void testExists() {
        storage.put("john_doe", new Trainee("john_doe", "John", "Doe", "password123", "01-01-2000", "elm street 123"));
        
        assertTrue(traineeDAO.exists("john_doe"));
        assertFalse(traineeDAO.exists("jane_doe"));
    }
    
    @Test
    void testGetStorage() {
        assertEquals(storage, traineeDAO.getStorage());
    }
    
    @Test
    void testDelete() {
        Trainee trainee = new Trainee("john_doe", "John", "Doe", "password123", "01-01-2000", "elm street 123");
        storage.put("john_doe", trainee);

        traineeDAO.delete("john_doe");
        assertFalse(storage.containsKey("john_doe"));
    }
}