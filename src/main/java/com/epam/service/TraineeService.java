package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.TraineeDAO;
import com.epam.model.Trainee;

@Service
public class TraineeService {

    @Autowired
    private TraineeDAO traineeDAO;

    // method to create a trainee
    public Trainee create(String firstName, String lastName, String dateOfBirth, String address) {
        String username = generateUsername(firstName, lastName);
        String password = generatePassword();
        Trainee trainee = new Trainee(username, firstName, lastName, password, dateOfBirth, address);
        traineeDAO.save(trainee);
        return trainee;
    }

    // method to update a trainee
    public Optional<Trainee> update(String username, String dateOfBirth, String address) {
        if (!traineeDAO.exists(username)) {
            throw new IllegalArgumentException("Trainee with username " + username + " not found"); // condition is not defined
        }
        Trainee trainee = traineeDAO.findByUsername(username);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        return Optional.of(trainee);
    }

    // method to delete a trainee
    public void delete(String username) throws IllegalArgumentException {
        if (!traineeDAO.exists(username)) {
            throw new IllegalArgumentException("Trainee with username " + username + " not found"); // condition is not defined
        }
        traineeDAO.delete(username);
    }

    // method to find a trainee by username
    public Optional<Trainee> findByUsername(String username) throws IllegalArgumentException {
        if (!traineeDAO.exists(username)) {
            throw new IllegalArgumentException("Trainee with username " + username + " not found");
        }
        return Optional.of(traineeDAO.findByUsername(username));
    }

    // auxiliar methods to generate username and password
    private String generateUsername(String firstName, String lastName) {
        String base = firstName + "." + lastName;
        String username = base;
        int suffix = 1;
        while (traineeDAO.exists(username)) {
            username = base + suffix;
            suffix++;
        }
        return suffix > 1 ? username : base;
    }

    private String generatePassword() {
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
