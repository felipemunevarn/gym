package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.impl.CreateReadUpdateDeleteDaoImpl;
import com.epam.model.Trainee;
import com.epam.util.AccountGenerator;

@Service
public class TraineeService {

    @Autowired
    private CreateReadUpdateDeleteDaoImpl<Trainee, String> traineeDAO;

    public Trainee create(String firstName, String lastName, String dateOfBirth, String address) {
        String username = AccountGenerator.generateUsername(
            firstName, 
            lastName, 
            traineeDAO::exists
        );
        String password = AccountGenerator.generatePassword();
        Trainee trainee = new Trainee.Builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .dateOfBirth(dateOfBirth)
                .address(address)
                .build();
        traineeDAO.save(trainee);
        return trainee;
    }
    
    public Optional<Trainee> update(String username, String dateOfBirth, String address) {
        if (!traineeDAO.exists(username)) {
            throw new IllegalArgumentException("Trainee with username " + username + " not found"); // condition is not defined
        }
        Trainee trainee = traineeDAO.findByUsername(username);
        // trainee.setDateOfBirth(dateOfBirth);
        // trainee.setAddress(address);
        traineeDAO.save(trainee);
        return Optional.of(trainee);
    }

    public void delete(String username) throws IllegalArgumentException {
        if (!traineeDAO.exists(username)) {
            throw new IllegalArgumentException("Trainee with username " + username + " not found"); // condition is not defined
        }
        traineeDAO.delete(username);
    }

    public Optional<Trainee> findByUsername(String username) throws IllegalArgumentException {
        if (!traineeDAO.exists(username)) {
            throw new IllegalArgumentException("Trainee with username " + username + " not found");
        }
        return Optional.of(traineeDAO.findByUsername(username));
    }
}
