package com.epam.facade;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.service.TraineeService;

@Component
public class GymFacade {
    private final TraineeService traineeService;
    // private final TrainerService trainerService;
    // private static Logger logger = LoggerFactory.getLogger(GymFacade.class);
    private Logger logger = LoggerFactory.getLogger(GymFacade.class);

    @Autowired
    public GymFacade(TraineeService ts) { //, TrainerService trs, TrainingService tgs) {
        this.traineeService = ts;
        // this.trainerService = trs;
        // this.trainingService = tgs;
    }

    // Methods to interact with services

    public TraineeService getTraineeService() {
        return traineeService;
    }

    public void createTrainee(String firstName, String lastName, String dateOfBirth, String email) {
        traineeService.create(firstName, lastName, dateOfBirth, email);
        logger.info("Trainee created successfully");
    }

    public void updateTrainee(String username, String dateOfBirth, String address) {
        traineeService.update(username, dateOfBirth, address);
        logger.info("Trainee updated successfully");
    }
    
    public void deleteTrainee(String username) {
        traineeService.delete(username);
        logger.info("Trainee deleted successfully");
    }

    public void findTraineeByUsername(String username) {
        try {
            traineeService.findByUsername(username);
            logger.info("Trainee found successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Trainee not found");
        }
    }
}
