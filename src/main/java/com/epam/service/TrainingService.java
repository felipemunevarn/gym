package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.TrainingDAO;
import com.epam.dao.GenericDAO;
// import com.epam.dao.TraineeDAO;
// import com.epam.dao.TrainerDAO;
import com.epam.model.Trainee;
import com.epam.model.Trainer;
import com.epam.model.Training;
import com.epam.model.TrainingType;

@Service
public class TrainingService {

    @Autowired
    private TrainingDAO trainingDAO;
    @Autowired
    private GenericDAO<Trainee, String> traineeDAO;
    @Autowired
    private GenericDAO<Trainer, String> trainerDAO;

    public Training create(String traineeName, String trainerName, TrainingType trainingType, String name, String date, String duration) {
        if (!traineeDAO.exists(traineeName)) {
            throw new IllegalArgumentException("Trainee with username " + traineeName + " not found"); // condition is not defined
        }
        if (!trainerDAO.exists(trainerName)) {
            throw new IllegalArgumentException("Trainer with username " + trainerName + " not found"); // condition is not defined
        }
        Training training = new Training(traineeName, trainerName, trainingType, name, date, duration);
        trainingDAO.save(training);
        return training;
    }

    public Optional<Training> findById(String id) throws IllegalArgumentException {
        if (!trainingDAO.exists(id)) {
            throw new IllegalArgumentException("Training with id " + id + " not found");
        }
        return Optional.of(trainingDAO.findById(id));
    }
}
