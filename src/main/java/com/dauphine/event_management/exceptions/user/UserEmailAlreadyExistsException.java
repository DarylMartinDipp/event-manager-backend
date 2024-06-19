package com.dauphine.event_management.exceptions.user;

public class UserEmailAlreadyExistsException extends Exception {
    public UserEmailAlreadyExistsException(String userEmail) {
        super("The email " + userEmail + " already exists.");
    }
}
