package com.dauphine.event_management.exceptions.registration;

import java.util.UUID;

public class RegistrationNotFoundByIdException extends Exception {
    public RegistrationNotFoundByIdException(UUID registrationId) {
        super("The registration designated by id " + registrationId + " was not found.");
    }
}
