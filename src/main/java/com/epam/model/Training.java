package com.epam.model;

import java.util.UUID;

public class Training implements Identifiable<String> {
    private String id;
    private String traineeName;
    private String trainerName;
    TrainingType trainingType;
    private String name;
    private String date;
    private String duration;

    @Override
    public String getId() {
        return id;
    }

    public Training(String traineeName, String trainerName, TrainingType trainingType, String name, String date, String duration) {
        this.id = UUID.randomUUID().toString();
        this.traineeName = traineeName;
        this.trainerName = trainerName;
        this.trainingType = trainingType;
        this.name = name;
        this.date = date;
        this.duration = duration;
    }
    // public String getId() {
    //     return id;
    // }
    // public void setId(String id) {
    //     this.id = id;
    // }
    public String getTraineeName() {
        return traineeName;
    }
    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }
    public String getTrainerName() {
        return trainerName;
    }
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
    public TrainingType getTrainingType() {
        return trainingType;
    }
    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    @Override
    public String toString() {
        return "Training [traineeName=" + traineeName + ", trainerName=" + trainerName + ", trainingType=" + trainingType
                + ", name=" + name + ", date=" + date + ", duration=" + duration + "]";
    }
}
