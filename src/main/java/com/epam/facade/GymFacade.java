package com.epam.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.model.TrainingType;
import com.epam.service.TraineeService;
import com.epam.service.TrainerService;
import com.epam.service.TrainingService;

@Component
public class GymFacade {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;
    private Logger logger = LoggerFactory.getLogger(GymFacade.class);

    @Autowired
    public GymFacade(TraineeService ts, TrainerService trs, TrainingService tgs) {
        this.traineeService = ts;
        this.trainerService = trs;
        this.trainingService = tgs;
    }

    // public TraineeService getTraineeService() {
    //     return traineeService;
    // }
    // public TrainerService getTrainerService() {
    //     return trainerService;
    // }

    // Trainee methods    
    public void createTrainee(String firstName, String lastName, String dateOfBirth, String address) {
        traineeService.create(firstName, lastName, dateOfBirth, address);
        logger.info("Trainee created successfully");
    }

    // public void updateTrainee(String username, String dateOfBirth, String address) {
    //     traineeService.update(username, dateOfBirth, address);
    //     logger.info("Trainee updated successfully");
    // }
    
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

    // Trainer methods
    public void createTrainer(String firstName, String lastName, String specialization) {
        trainerService.create(firstName, lastName, specialization);
        logger.info("Trainer created successfully");
    }

    public void updateTrainer(String username, String specialization) {
        trainerService.update(username, specialization);
        logger.info("Trainer updated successfully");
    }

    public void findTrainerByUsername(String username) {
        try {
            trainerService.findByUsername(username);
            logger.info("Trainer found successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Trainer not found");
        }
    }

    // Training methods
    public void createTraining(String traineeName, String trainerName, TrainingType trainingType, String name, String date, String duration) {
        trainingService.create(traineeName, trainerName, trainingType, name, date, duration);
        logger.info("Training created successfully");
    }

    public void findTrainingById(String id) {
        try {
            trainingService.findById(id);
            logger.info("Training found successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Training not found");
        }
    }
}
