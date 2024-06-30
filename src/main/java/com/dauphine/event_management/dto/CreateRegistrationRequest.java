package com.dauphine.event_management.dto;

import java.util.UUID;

public class CreateRegistrationRequest {
    private UUID userId;
    private UUID eventId;

    public CreateRegistrationRequest(UUID userId, UUID eventId) {
        this.userId = userId;
        this.eventId = eventId;
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
