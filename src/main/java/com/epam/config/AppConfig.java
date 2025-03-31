package com.epam.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.epam.model.Trainee;
import com.epam.model.Trainer;
import com.epam.model.Training;
import com.epam.model.TrainingType;

import jakarta.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.epam")
public class AppConfig {

    @Value("${storage.trainee.file}")
    private String traineeFilePath;
    @Value("${storage.trainer.file}")
    private String trainerFilePath;
    @Value("${storage.training.file}")
    private String trainingFilePath;

    @Bean
    public Map<String, Trainee> traineeStorage() {
        return new HashMap<>();
    }

    @Bean
    public Map<String, Trainer> trainerStorage() {
        return new HashMap<>();
    }
    
    @Bean
    public Map<String, Training> trainingStorage() {
        return new HashMap<>();
    }

    @PostConstruct
    public void initializeTraineeStorage() throws IOException {
        // trainee
        Map<String, Trainee> traineeStorage = traineeStorage();
        Map<String, Trainee> teLoaded = loadFromFile(traineeFilePath, Trainee.class);
        traineeStorage.putAll(teLoaded);
        
        // trainer
        Map<String, Trainer> trainerStorage = trainerStorage();
        Map<String, Trainer> trLoaded = loadFromFile(trainerFilePath, Trainer.class);
        trainerStorage.putAll(trLoaded);

        //training
        Map<String, Training> trainingStorage = trainingStorage();
        Map<String, Training> tgLoaded = loadFromFile(trainingFilePath, Training.class);
        trainingStorage.putAll(tgLoaded);
    }

    private <T> Map<String, T> loadFromFile(String file, Class<T> type) {
        Map<String, T> storage = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
                if (type == Trainee.class) {
                    T entity = type.cast(new Trainee.Builder()
                    .username(parts[0])
                    .firstName(parts[1])
                    .lastName(parts[2])
                    .password(parts[3])
                    .dateOfBirth(parts[4])
                    .address(parts[5])
                    .build());
                    storage.put(parts[0], entity);
                } else if (type == Trainer.class) {
                    T entity = type.cast(new Trainer.Builder()
                    .username(parts[0])
                    .firstName(parts[1])
                    .lastName(parts[2])
                    .password(parts[3])
                    .specialization(parts[4])
                    .build());
                    storage.put(parts[0], entity);
                } else if (type == Training.class) {
                    T entity = type.cast(new Training(parts[1], parts[2], TrainingType.valueOf(parts[3]), parts[4], parts[5], parts[6]));
                    storage.put(parts[0], entity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + file, e);
        }
        return storage;
    }
}