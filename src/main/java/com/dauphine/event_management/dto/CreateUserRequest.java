package com.dauphine.event_management.dto;

public class CreateUserRequest {
    private String userEmail;
    private String username;
    private String hashed_password;

    public CreateUserRequest(String userEmail, String username, String hashed_password) {
        this.userEmail = userEmail;
        this.username = username;
        this.hashed_password = hashed_password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }
}
