package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.TrainerDAO;
import com.epam.model.Trainer;

@Service
public class TrainerService {

    @Autowired
    private TrainerDAO trainerDAO;

    public Trainer create(String firstName, String lastName, String specialization) {
        String username = generateUsername(firstName, lastName);
        String password = generatePassword();
        Trainer trainer = new Trainer(username, firstName, lastName, password, specialization);
        trainerDAO.save(trainer);
        return trainer;
    }

    public Optional<Trainer> update(String username, String specialization) {
        if (!trainerDAO.exists(username)) {
            throw new IllegalArgumentException("Trainer with username " + username + " not found"); // condition is not defined
        }
        Trainer trainer = trainerDAO.findByUsername(username);
        trainer.setSpecialization(specialization);
        return Optional.of(trainer);
    }

    public Optional<Trainer> findByUsername(String username) throws IllegalArgumentException {
        if (!trainerDAO.exists(username)) {
            throw new IllegalArgumentException("Trainer with username " + username + " not found");
        }
        return Optional.of(trainerDAO.findByUsername(username));
    }

    private String generateUsername(String firstName, String lastName) {
        String base = firstName + "." + lastName;
        String username = base;
        int suffix = 1;
        while (trainerDAO.exists(username)) {
            username = base + suffix;
            suffix++;
        }
        return suffix > 1 ? username : base;
    }

    private String generatePassword() {
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
