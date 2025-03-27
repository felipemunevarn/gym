package com.epam.model;

public class Training {
    private String traineeName;
    private String trainerName;
    TrainingType.Type trainingType;
    private String name;
    private String date;
    private String duration;

    public Training(String traineeName, String trainerName, TrainingType.Type trainingType, String name, String date, String duration) {
        this.traineeName = traineeName;
        this.trainerName = trainerName;
        this.trainingType = trainingType;
        this.name = name;
        this.date = date;
        this.duration = duration;
    }
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
    public TrainingType.Type getTrainingType() {
        return trainingType;
    }
    public void setTrainingType(TrainingType.Type trainingType) {
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
