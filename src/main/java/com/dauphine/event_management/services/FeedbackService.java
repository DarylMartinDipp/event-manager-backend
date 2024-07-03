package com.dauphine.event_management.services;

import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.feedback.FeedbackNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Feedback;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();

    Feedback getFeedbackById(UUID feedbackId) throws FeedbackNotFoundByIdException;

    List<Feedback> getFeedbacksByEventId(UUID eventId) throws EventNotFoundByIdException;

    List<Feedback> getFeedbacksByUserId(UUID userId) throws UserNotFoundByIdException;

    Feedback createFeedback(String feedback, short rating, UUID userId, UUID eventId) throws UserNotFoundByIdException,
            EventNotFoundByIdException;

    Feedback updateFeedback(UUID feedbackId, UUID userId, String feedback, short rating, UUID eventId)
            throws FeedbackNotFoundByIdException, UserNotFoundByIdException, EventNotFoundByIdException;

    void deleteFeedbackById(UUID feedbackId) throws FeedbackNotFoundByIdException;
}
