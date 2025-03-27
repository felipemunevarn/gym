package com.epam;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.config.AppConfig;
import com.epam.facade.GymFacade;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        GymFacade facade = context.getBean("gymFacade", GymFacade.class);
        facade.createTrainee("John", "Doe", "01/01/2000", "123 Main St");
        // System.out.println(facade.getTraineeService().findByUsername("John.Doe"));
        
        context.close();
    }
}