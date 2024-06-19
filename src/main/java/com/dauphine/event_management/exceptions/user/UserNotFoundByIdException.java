package com.dauphine.event_management.exceptions.user;

import java.util.UUID;

public class UserNotFoundByIdException extends Exception {
    public UserNotFoundByIdException(UUID userId) {
        super("The user designated by id " + userId + " was not found.");
    }
}
