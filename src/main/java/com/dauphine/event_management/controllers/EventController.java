package com.dauphine.event_management.controllers;

import com.dauphine.event_management.dto.CreateEventRequest;
import com.dauphine.event_management.exceptions.category.CategoryNotFoundByIdException;
import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Event;
import com.dauphine.event_management.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/events")
@Tag(name = "Event Controller API", description = "Event-related endpoints")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @Operation(
            summary = "Get all events endpoint",
            description = "Return all events that are in the database, sorted" +
                    "by the event date."
    )
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String title) {
        List<Event> eventsToGet = eventService.getAllEvents();
        eventsToGet.sort(Comparator.comparing(Event::getEvent_date));
        return ResponseEntity.ok(eventsToGet);
    }

    @GetMapping("{eventId}")
    @Operation(
            summary = "Get an event by ID endpoint",
            description = "Return a certain event according to its id."
    )
    public ResponseEntity<Event> getEventById(@PathVariable UUID eventId) {
        try {
            final Event eventToGet = eventService.getEventById(eventId);
            return ResponseEntity.ok(eventToGet);
        } catch (EventNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a new event endpoint",
            description = "Create a new event with all its data."
    )
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequest eventToCreate) {
        try {
            Event event = eventService.createEvent(eventToCreate.getEventTitle(), eventToCreate.getEventDescription(),
                    eventToCreate.getEventNumberStreet(), eventToCreate.getEventStreet(), eventToCreate.getEventCity(),
                    eventToCreate.getEventCountry(), eventToCreate.getEventDate(), eventToCreate.getOrganizerId(),
                    eventToCreate.getCategoryId());
            return ResponseEntity
                    .created(URI.create("v1/events/" + event.getId()))
                    .body(event);
        } catch (UserNotFoundByIdException | CategoryNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{eventId}")
    @Operation(
            summary = "Update an event endpoint",
            description = "Update an event according to the id."
    )
    public ResponseEntity<Event> updateEvent(@PathVariable UUID eventId,
                                             @RequestBody CreateEventRequest eventToUpdate) {
        try {
            Event event = eventService.updateEvent(eventId, eventToUpdate.getEventTitle(),
                    eventToUpdate.getEventDescription(), eventToUpdate.getEventNumberStreet(),
                    eventToUpdate.getEventStreet(), eventToUpdate.getEventCity(), eventToUpdate.getEventCountry(),
                    eventToUpdate.getEventDate(), eventToUpdate.getOrganizerId(), eventToUpdate.getCategoryId());
            return ResponseEntity.ok(event);
        } catch (EventNotFoundByIdException | UserNotFoundByIdException | CategoryNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{eventId}")
    @Operation(
            summary = "Delete an event endpoint",
            description = "Delete an existing event according to the id."
    )
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID eventId) {
        try {
            eventService.deleteEventById(eventId);
            return ResponseEntity.ok().build();
        } catch (EventNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
