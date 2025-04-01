package com.epam.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.dao.impl.CreateReadDaoImpl;
import com.epam.dao.impl.CreateReadUpdateDaoImpl;
import com.epam.dao.impl.CreateReadUpdateDeleteDaoImpl;
import com.epam.model.Trainee;
import com.epam.model.Trainer;
import com.epam.model.Training;

@Configuration
public class DaoConfig {

    @Bean
    public CreateReadUpdateDeleteDaoImpl<Trainee, String> traineeDAO(@Qualifier("traineeStorage") Map<String, Trainee> storage) {
        return new CreateReadUpdateDeleteDaoImpl<>(storage); 
    }

    @Bean
    public CreateReadUpdateDaoImpl<Trainer, String> trainerDAO(@Qualifier("trainerStorage") Map<String, Trainer> storage) {
        return new CreateReadUpdateDaoImpl<>(storage); 
    }

    @Bean
    public CreateReadDaoImpl<Training, String> trainingDAO(@Qualifier("trainingStorage") Map<String, Training> storage) {
        return new CreateReadDaoImpl<>(storage);
    }
}