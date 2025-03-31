package com.epam.model;

public class Trainee {
    private final User user;
    private final String dateOfBirth;
    private final String address;

    private Trainee(Builder builder) {
        this.user = builder.user;
        this.dateOfBirth = builder.dateOfBirth;
        this.address = builder.address;
    }

    public String getUsername() { return user.getUsername(); }
    public String getFirstName() { return user.getFirstName(); }
    public String getLastName() { return user.getLastName(); }
    public String getPassword() { return user.getPassword(); }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getAddress() { return address; }

    public static class Builder {
        private User user;
        private String dateOfBirth;
        private String address;

        public Builder username(String username) {
            this.user = new User(username, "", "", "");
            return this;
        }

        public Builder firstName(String firstName) {
            this.user = new User(user.getUsername(), firstName, user.getLastName(), user.getPassword());
            return this;
        }

        public Builder lastName(String lastName) {
            this.user = new User(user.getUsername(), user.getFirstName(), lastName, user.getPassword());
            return this;
        }

        public Builder password(String password) {
            this.user = new User(user.getUsername(), user.getFirstName(), user.getLastName(), password);
            return this;
        }

        public Builder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Trainee build() {
            return new Trainee(this);
        }
    }

    @Override
    public String toString() {
        return "Trainee [username=" + getUsername() + ", firstName=" + getFirstName() 
        + ", lastName=" + getLastName() + ", password=" + getPassword()
        + ", dateOfBirth=" + getDateOfBirth() + ", address=" + getAddress() + "]";
    }
}
