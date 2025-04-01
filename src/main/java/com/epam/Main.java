package com.epam;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.config.AppConfig;
import com.epam.facade.GymFacade;
import com.epam.model.TrainingType;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        GymFacade facade = context.getBean("gymFacade", GymFacade.class);
        facade.createTrainee("John", "Doe", "01/01/2000", "123 Main St");
        facade.createTrainer("John", "Doe", "yoga");
        facade.createTraining("John.Doe", "John.Doe", TrainingType.YOGA, "morning yoga", "28-03-2025", "1 and a half hours");
        facade.findTraineeByUsername("John.Doe");
        facade.findTrainerByUsername("John.Doe");
        facade.findTraineeByUsername("John.Doe1");
        facade.findTrainerByUsername("John.Doe1");
        facade.findTraineeByUsername("John.Doe2");
        facade.findTrainerByUsername("John.Doe2");
        facade.findTraineeByUsername("John.Doe10");
        facade.findTrainerByUsername("John.Doe10");
        facade.findTrainingById("training001");
        facade.findTrainingById("training007");
        context.close();
    }
}