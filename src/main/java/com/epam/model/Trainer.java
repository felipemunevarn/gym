package com.epam.model;

public class Trainer extends User {
    private String specialization;

    public Trainer(String username, String firstName, String lastName, String password, String specialization) {
        super(username, firstName, lastName, password);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    @Override
    public String toString() {
        return "Trainer [username=" + getUsername() + ", firstName=" + getFirstName() 
        + ", lastName=" + getLastName() + ", password=" + getPassword()
        + ", specialization=" + getSpecialization() + "]";
    }
}
