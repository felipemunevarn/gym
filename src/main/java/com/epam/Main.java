package com.epam;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.config.AppConfig;
import com.epam.model.Trainee;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        @SuppressWarnings("unchecked")
        Map<String, Trainee> traineeStorage = context.getBean("traineeStorage", Map.class);
        System.out.println(traineeStorage);
        context.close();
    }
}