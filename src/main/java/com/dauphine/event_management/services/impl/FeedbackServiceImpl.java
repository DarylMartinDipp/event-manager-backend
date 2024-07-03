package com.dauphine.event_management.services.impl;

import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.feedback.FeedbackNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Event;
import com.dauphine.event_management.models.Feedback;
import com.dauphine.event_management.models.User;
import com.dauphine.event_management.repositories.FeedbackRepository;
import com.dauphine.event_management.services.EventService;
import com.dauphine.event_management.services.FeedbackService;
import com.dauphine.event_management.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final EventService eventService;
    private final UserService userService;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, EventService eventService, UserService userService) {
        this.feedbackRepository = feedbackRepository;
        this.eventService = eventService;
        this.userService = userService;
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(UUID feedbackId) throws FeedbackNotFoundByIdException {
        return feedbackRepository.findById(feedbackId).orElseThrow(
                () -> new FeedbackNotFoundByIdException(feedbackId)
        );
    }

    @Override
    public List<Feedback> getFeedbacksByEventId(UUID eventId) throws EventNotFoundByIdException {
        Event event = eventService.getEventById(eventId);
        return feedbackRepository.findByEventId(event);
    }

    @Override
    public List<Feedback> getFeedbacksByUserId(UUID userId) throws UserNotFoundByIdException {
        User user = userService.getUserById(userId);
        return feedbackRepository.findByUserId(user);
    }

    @Override
    public Feedback createFeedback(String feedback, short rating, UUID userId, UUID eventId) throws UserNotFoundByIdException, EventNotFoundByIdException {
        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId);

        Feedback newFeedback = new Feedback(feedback, rating, user, event);
        return feedbackRepository.save(newFeedback);
    }

    @Override
    public Feedback updateFeedback(UUID feedbackId, UUID userId, String feedback, short rating, UUID eventId) throws FeedbackNotFoundByIdException, UserNotFoundByIdException, EventNotFoundByIdException {
        Feedback feedbackToUpdate = getFeedbackById(feedbackId);

        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId);

        feedbackToUpdate.setUser_id(user);
        feedbackToUpdate.setEvent_id(event);

        return feedbackRepository.save(feedbackToUpdate);
    }

    @Override
    public void deleteFeedbackById(UUID feedbackId) throws FeedbackNotFoundByIdException {
        getFeedbackById(feedbackId);
        feedbackRepository.deleteById(feedbackId);
    }
}
