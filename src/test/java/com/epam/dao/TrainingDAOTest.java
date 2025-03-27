package com.epam.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.epam.model.Training;
import com.epam.model.TrainingType;

class TrainingDAOTest {

    private TrainingDAO trainingDAO;
    private Map<String, Training> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        trainingDAO = new TrainingDAO(storage);
    }

    @Test
    void testSave() {
        Training training = new Training("john.doe","jane.smith",TrainingType.valueOf("YOGA"),"morning yoga","20-03-2025","1 hour");
        trainingDAO.save(training);
        String id = training.getId();

        assertTrue(storage.containsKey(id));
        assertEquals(training, storage.get(id));
    }
    
    @Test
    void testFindById() {
        Training training = new Training("john.doe","jane.smith",TrainingType.valueOf("YOGA"),"morning yoga","20-03-2025","1 hour");
        storage.put("training001", training);
        
        Training foundTraining = trainingDAO.findById("training001");
        
        assertNotNull(foundTraining);
        assertEquals(training, foundTraining);
    }
    
    @Test
    void testFindByIdWhenNotExists() {
        Training foundTraining = trainingDAO.findById("nonexistent");
        
        assertNull(foundTraining);
    }
    // "training002,jane.smith,john.doe,CARDIO,evening cardio,21-03-2025,1 hour"

    @Test
    void testExists() {
        storage.put("training001", new Training("john.doe","jane.smith",TrainingType.valueOf("YOGA"),"morning yoga","20-03-2025","1 hour"));

        assertTrue(trainingDAO.exists("training001"));
        assertFalse(trainingDAO.exists("nonexistent"));
    }

    @Test
    void testGetStorage() {
        Training training = new Training("john.doe","jane.smith",TrainingType.valueOf("YOGA"),"morning yoga","20-03-2025","1 hour");
        storage.put("training001", training);

        Map<String, Training> retrievedStorage = trainingDAO.getStorage();

        assertEquals(storage, retrievedStorage);
        assertEquals(1, retrievedStorage.size());
        assertTrue(retrievedStorage.containsKey("training001"));
    }
}