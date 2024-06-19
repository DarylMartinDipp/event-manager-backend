package com.dauphine.event_management.dto;

public class CreateUserRequest {
    private String userEmail;
    private String username;
    private String hashedPassword;

    public CreateUserRequest(String userEmail, String username, String hashedPassword) {
        this.userEmail = userEmail;
        this.username = username;
        this.hashedPassword = hashedPassword;
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
