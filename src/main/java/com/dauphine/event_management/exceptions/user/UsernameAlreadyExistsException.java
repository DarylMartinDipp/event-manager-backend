package com.dauphine.event_management.exceptions.user;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String username) {
        super("The username " + username + " is already taken.");
    }
}
