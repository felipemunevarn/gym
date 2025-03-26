package com.epam.model;

public class Trainee extends User {
    private String dateOfBirth;
    private String address;

    public Trainee(String username, String firstName, String lastName, String password, String dateOfBirth, String address) {
        super(username, firstName, lastName, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Trainee [username=" + getUsername() + ", firstName=" + getFirstName() 
        + ", lastName=" + getLastName() + ", password=" + getPassword()
        + ", dateOfBirth=" + getDateOfBirth() + ", address=" + getAddress() + "]";
    }
}
