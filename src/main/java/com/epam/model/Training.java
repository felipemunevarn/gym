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

    private Training(Builder builder) {
        this.id = UUID.randomUUID().toString();
        // this.id = builder.id;
        this.traineeName = builder.traineeName;
        this.trainerName = builder.trainerName;
        this.trainingType = builder.trainingType;
        this.name = builder.name;
        this.date = builder.date;
        this.duration = builder.duration;
    }
    public String getTraineeName() { return traineeName; }
    public String getTrainerName() { return trainerName; }
    public TrainingType getTrainingType() { return trainingType; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getDuration() { return duration; }
    
    public static class Builder {
        // private String id;
        private String traineeName;
        private String trainerName;
        private TrainingType trainingType;
        private String name;
        private String date;
        private String duration;

        // public Builder id(String id) {
        //     this.id = id;
        //     return this;
        // }

        public Builder traineeName(String traineeName) {
            this.traineeName = traineeName;
            return this;
        }

        public Builder trainerName(String trainerName) {
            this.trainerName = trainerName;
            return this;
        }

        public Builder trainingType(TrainingType trainingType) {
            this.trainingType = trainingType;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder duration(String duration) {
            this.duration = duration;
            return this;
        }

        public Training build() {
            return new Training(this);
        }
    }

    @Override
    public String toString() {
        return "Training [traineeName=" + traineeName + ", trainerName=" + trainerName + ", trainingType=" + trainingType
                + ", name=" + name + ", date=" + date + ", duration=" + duration + "]";
    }
}
