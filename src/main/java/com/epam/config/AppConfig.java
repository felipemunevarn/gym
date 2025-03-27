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
import jakarta.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.epam")
public class AppConfig {

    @Value("${storage.trainee.file}")
    private String traineeFilePath;

    @Bean
    public Map<String, Trainee> traineeStorage() {
        return new HashMap<>();
    }

    @PostConstruct
    public void initializeTraineeStorage() throws IOException {
        Map<String, Trainee> storage = traineeStorage();
        Map<String, Trainee> loaded = loadFromFile(traineeFilePath, Trainee.class);
        storage.putAll(loaded);
    }

    private <T> Map<String, T> loadFromFile(String file, Class<T> type) {
        Map<String, T> storage = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
                // if (type == Trainee.class) {
                    T entity = type.cast(new Trainee(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                    storage.put(parts[0], entity);
                // } else if (type == Trainer.class) {
                //     T entity = type.cast(new Trainer(parts[0], parts[1], parts[2], parts[3], parts[4]));
                //     storage.put(parts[0], entity);
                // }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + file, e);
        }
        return storage;
    }
}