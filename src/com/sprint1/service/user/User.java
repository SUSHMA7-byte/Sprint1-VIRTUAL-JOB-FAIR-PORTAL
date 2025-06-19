package com.sprint1.service.user;

public abstract class User {
    private String fullName;
    private String email;
    private String phoneNumber;

    public User(String fullName, String email, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public abstract String getRoleDescription();
}
