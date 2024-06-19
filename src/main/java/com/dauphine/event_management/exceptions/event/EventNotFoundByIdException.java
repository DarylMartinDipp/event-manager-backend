package com.dauphine.event_management.exceptions.event;

import java.util.UUID;

public class EventNotFoundByIdException extends Exception {
    public EventNotFoundByIdException(UUID eventId) {
        super("The event designated by id " + eventId + " was not found.");
    }
}
