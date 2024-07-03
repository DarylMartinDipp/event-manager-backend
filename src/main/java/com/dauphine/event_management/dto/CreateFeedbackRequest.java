package com.dauphine.event_management.dto;

import java.util.UUID;

public class CreateFeedbackRequest {
    private String feedback;
    private short rating;
    private UUID userId;
    private UUID eventId;

    public CreateFeedbackRequest(String feedback, short rating, UUID userId, UUID eventId) {
        this.feedback = feedback;
        this.rating = rating;
        this.userId = userId;
        this.eventId = eventId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
