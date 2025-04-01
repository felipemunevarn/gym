package com.epam.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.dao.GenericDAO;
import com.epam.dao.InMemoryGenericDAO;
import com.epam.model.Trainee;
import com.epam.model.Trainer;

@Configuration
public class DaoConfig {

    @Bean
    public GenericDAO<Trainee, String> traineeDAO(@Qualifier("traineeStorage") Map<String, Trainee> storage) {
        return new InMemoryGenericDAO<>(storage); // Injects traineeStorage
    }

    // @Bean
    // public GenericDAO<Trainer, String> trainerDAO(@Qualifier("trainerStorage") Map<String, Trainer> storage) {
    //     return new InMemoryGenericDAO<>(storage); // Injects trainerStorage
    // }
}