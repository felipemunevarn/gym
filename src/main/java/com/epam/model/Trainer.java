package com.epam.model;

public class Trainer implements Identifiable<String> {
    private final User user;
    private final String specialization;

    @Override
    public String getId() {
        return user.getUsername();
    }

    private Trainer(Builder builder) {
        this.user = builder.user;
        this.specialization = builder.specialization;
    }

    public String getUsername() { return user.getUsername(); }
    public String getFirstName() { return user.getFirstName(); }
    public String getLastName() { return user.getLastName(); }
    public String getPassword() { return user.getPassword(); }
    public String getSpecialization() { return specialization; }
    
    public static class Builder {
        private User user;
        private String specialization;

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

        public Builder specialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public Trainer build() {
            return new Trainer(this);
        }
    }

    @Override
    public String toString() {
        return "Trainer [username=" + getUsername() + ", firstName=" + getFirstName() 
        + ", lastName=" + getLastName() + ", password=" + getPassword()
        + ", specialization=" + getSpecialization() + "]";
    }
}
