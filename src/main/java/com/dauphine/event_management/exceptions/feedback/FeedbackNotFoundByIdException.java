package com.dauphine.event_management.exceptions.feedback;

import java.util.UUID;

public class FeedbackNotFoundByIdException extends Exception {
    public FeedbackNotFoundByIdException(UUID feedbackId) {
        super("The feedback designated by id " + feedbackId + " was not found.");
    }
}
