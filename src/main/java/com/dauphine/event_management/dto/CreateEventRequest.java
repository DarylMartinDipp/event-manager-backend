package com.dauphine.event_management.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateEventRequest {
    private String eventTitle;
    private String eventDescription;
    private short eventNumberStreet;
    private String eventStreet;
    private String eventCity;
    private String eventCountry;
    private LocalDateTime eventDate;
    private UUID organizerId;
    private UUID categoryId;

    public CreateEventRequest(String eventTitle, String eventDescription, short eventNumberStreet, String eventStreet,
                              String eventCity, String eventCountry, LocalDateTime eventDate, UUID organizerId,
                              UUID categoryId) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventNumberStreet = eventNumberStreet;
        this.eventStreet = eventStreet;
        this.eventCity = eventCity;
        this.eventCountry = eventCountry;
        this.eventDate = eventDate;
        this.organizerId = organizerId;
        this.categoryId = categoryId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public short getEventNumberStreet() {
        return eventNumberStreet;
    }

    public void setEventNumberStreet(short eventNumberStreet) {
        this.eventNumberStreet = eventNumberStreet;
    }

    public String getEventStreet() {
        return eventStreet;
    }

    public void setEventStreet(String eventStreet) {
        this.eventStreet = eventStreet;
    }

    public String getEventCity() {
        return eventCity;
    }

    public void setEventCity(String eventCity) {
        this.eventCity = eventCity;
    }

    public String getEventCountry() {
        return eventCountry;
    }

    public void setEventCountry(String eventCountry) {
        this.eventCountry = eventCountry;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public UUID getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(UUID organizerId) {
        this.organizerId = organizerId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
