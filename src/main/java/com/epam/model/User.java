package com.epam.model;

public class User {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final boolean isActive = true;

    public User(final String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    // public void setUsername(String username) {
    //     this.username = username;
    // }
    public String getFirstName() {
        return firstName;
    }
    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }
    public String getLastName() {
        return lastName;
    }
    // public void setLastName(String lastName) {
    //     this.lastName = lastName;
    // }
    public String getPassword() {
        return password;
    }
    // public void setPassword(String password) {
    //     this.password = password;
    // }
    public boolean isActive() {
        return isActive;
    }
    // public void setActive(boolean active) {
    //     isActive = active;
    // }
}

