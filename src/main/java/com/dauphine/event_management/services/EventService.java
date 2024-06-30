package com.dauphine.event_management.services;

import com.dauphine.event_management.exceptions.category.CategoryNotFoundByIdException;
import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    List<Event> getAllEvents();

    Event getEventById(UUID eventId) throws EventNotFoundByIdException;

    List<Event> getEventsByTitle(String eventTitle);

    List<Event> getEventsByCity(String eventCity);

    Optional<Event> getEventsByCountry(String eventCountry);

    Event createEvent(String eventTitle, String eventDescription, short eventNumberStreet, String eventStreet,
                      String eventCity, String eventCountry, LocalDateTime eventDate, UUID organizerId,
                      UUID categoryId) throws UserNotFoundByIdException, CategoryNotFoundByIdException;

    Event updateEvent(UUID eventId, String eventTitle, String eventDescription, short eventNumberStreet,
                      String eventStreet, String eventCity, String eventCountry, LocalDateTime eventDate,
                      UUID organizerId, UUID categoryId) throws EventNotFoundByIdException, UserNotFoundByIdException,
                      CategoryNotFoundByIdException;

    void deleteEventById(UUID eventId) throws EventNotFoundByIdException;
}
