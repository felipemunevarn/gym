package com.epam.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.epam.model.Training;

@Repository
public class TrainingDAO {
    private final Map<String, Training> storage;

    @Autowired
    public TrainingDAO(@Qualifier("trainingStorage") Map<String, Training> storage) {
        this.storage = storage;
    }

    public void save(Training training) {
        storage.put(training.getId(), training);
    }

    public Training findById(String id) {
        return storage.get(id);
    }

    public boolean exists(String id) {
        return storage.containsKey(id);
    }
 
    public Map<String, Training> getStorage() {
        return storage;
    }
}