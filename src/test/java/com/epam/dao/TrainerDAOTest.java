package com.epam.dao;

import com.epam.model.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class TrainerDAOTest {

    private TrainerDAO trainerDAO;
    private Map<String, Trainer> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        trainerDAO = new TrainerDAO(storage);
    }

    @Test
    void testSave() {
        Trainer trainer = new Trainer("john_doe", "John", "Doe", "password123", "stretching");
        trainerDAO.save(trainer);
        
        assertTrue(storage.containsKey("john_doe"));
        assertEquals(trainer, storage.get("john_doe"));
    }
    
    @Test
    void testFindByUsername() {
        Trainer trainer = new Trainer("john_doe", "John", "Doe", "password123", "stretching");
        storage.put("john_doe", trainer);
        
        Trainer result = trainerDAO.findByUsername("john_doe");
        assertNotNull(result);
        assertEquals(trainer, result);
    }
    
    @Test
    void testExists() {
        storage.put("john_doe", new Trainer("john_doe", "John", "Doe", "password123", "stretching"));
        
        assertTrue(trainerDAO.exists("john_doe"));
        assertFalse(trainerDAO.exists("jane_doe"));
    }
    
    @Test
    void testGetStorage() {
        assertEquals(storage, trainerDAO.getStorage());
    }
    
    @Test
    void testDelete() {
        Trainer trainer = new Trainer("john_doe", "John", "Doe", "password123", "stretching");
        storage.put("john_doe", trainer);

        trainerDAO.delete("john_doe");
        assertFalse(storage.containsKey("john_doe"));
    }
}