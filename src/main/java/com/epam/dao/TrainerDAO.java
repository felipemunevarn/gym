package com.epam.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.epam.model.Trainer;

@Repository
public class TrainerDAO {
    private final Map<String, Trainer> storage;

    @Autowired
    public TrainerDAO(@Qualifier("trainerStorage") Map<String, Trainer> storage) {
        this.storage = storage;
    }

    public void save(Trainer trainer) {
        storage.put(trainer.getUsername(), trainer);
    }

    public Trainer findByUsername(String username) {
        return storage.get(username);
    }

    public boolean exists(String username) {
        return storage.containsKey(username);
    }
 
    public Map<String, Trainer> getStorage() {
        return storage;
    }

    public void delete(String username) {
        storage.remove(username);
    }
}