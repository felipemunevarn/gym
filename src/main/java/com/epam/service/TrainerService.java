package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.impl.CreateReadUpdateDaoImpl;
import com.epam.model.Trainer;
import com.epam.util.AccountGenerator;

@Service
public class TrainerService {

    @Autowired
    private CreateReadUpdateDaoImpl<Trainer, String> trainerDAO;

    public Trainer create(String firstName, String lastName, String specialization) {
        String username = AccountGenerator.generateUsername(
            firstName, 
            lastName, 
            trainerDAO::exists
        );
        String password = AccountGenerator.generatePassword();
        Trainer trainer = new Trainer.Builder()
        .username(username)
        .firstName(firstName)
        .lastName(lastName)
        .password(password)
        .specialization(specialization)
        .build();
        trainerDAO.save(trainer);
        return trainer;
    }
    
    public Optional<Trainer> update(String username, String specialization) {
        if (!trainerDAO.exists(username)) {
            throw new IllegalArgumentException("Trainer with username " + username + " not found"); // condition is not defined
        }
        Trainer trainer = trainerDAO.findByUsername(username);
        // trainer.setSpecialization(specialization);
        trainerDAO.save(trainer);
        return Optional.of(trainer);
    }

    public Optional<Trainer> findByUsername(String username) throws IllegalArgumentException {
        if (!trainerDAO.exists(username)) {
            throw new IllegalArgumentException("Trainer with username " + username + " not found");
        }
        return Optional.of(trainerDAO.findByUsername(username));
    }
}
