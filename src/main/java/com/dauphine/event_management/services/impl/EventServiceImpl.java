package com.dauphine.event_management.services.impl;

import com.dauphine.event_management.exceptions.category.CategoryNotFoundByIdException;
import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Category;
import com.dauphine.event_management.models.Event;
import com.dauphine.event_management.models.User;
import com.dauphine.event_management.repositories.EventRepository;
import com.dauphine.event_management.services.CategoryService;
import com.dauphine.event_management.services.EventService;
import com.dauphine.event_management.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    public EventServiceImpl(EventRepository eventRepository, CategoryService categoryService,
                            UserService userService) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(UUID eventId) throws EventNotFoundByIdException {
        return eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundByIdException(eventId));
    }

    @Override
    public List<Event> getEventsByTitle(String eventTitle) {
        return eventRepository.findByTitleContainingIgnoreCase(eventTitle);
    }

    @Override
    public List<Event> getEventsByCity(String eventCity) {
        return eventRepository.findByCity(eventCity);
    }

    @Override
    public Optional<Event> getEventsByCountry(String eventCountry) {
        return eventRepository.findByCountry(eventCountry);
    }

    @Override
    public Event createEvent(String eventTitle, String eventDescription, short eventNumberStreet, String eventStreet,
                             String eventCity, String eventCountry, LocalDateTime eventDate, UUID organizerId,
                             UUID categoryId) throws UserNotFoundByIdException, CategoryNotFoundByIdException {
        User organizer = userService.getUserById(organizerId);
        Category category = categoryService.getCategoryById(categoryId);

        Event newEvent = new Event(eventTitle, eventDescription, eventNumberStreet, eventStreet, eventCity,
                eventCountry, eventDate, organizer, category);
        return eventRepository.save(newEvent);
    }

    @Override
    public Event updateEvent(UUID eventId, String eventTitle, String eventDescription, short eventNumberStreet,
                             String eventStreet, String eventCity, String eventCountry, LocalDateTime eventDate,
                             UUID organizerId, UUID categoryId)
            throws EventNotFoundByIdException, UserNotFoundByIdException, CategoryNotFoundByIdException {
        Event eventToUpdate = getEventById(eventId);

        User organizer = userService.getUserById(organizerId);
        Category category = categoryService.getCategoryById(categoryId);

        eventToUpdate.setTitle(eventTitle);
        eventToUpdate.setDescription(eventDescription);
        eventToUpdate.setNumber_street(eventNumberStreet);
        eventToUpdate.setStreet(eventStreet);
        eventToUpdate.setCity(eventCity);
        eventToUpdate.setCountry(eventCountry);
        eventToUpdate.setEvent_date(eventDate);
        eventToUpdate.setOrganizer_id(organizer);
        eventToUpdate.setCategory_id(category);

        return eventRepository.save(eventToUpdate);
    }

    @Override
    public void deleteEventById(UUID eventId) throws EventNotFoundByIdException {
        getEventById(eventId);
        eventRepository.deleteById(eventId);
    }
}
