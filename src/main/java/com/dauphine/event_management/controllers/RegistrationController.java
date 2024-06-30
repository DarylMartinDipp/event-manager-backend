package com.dauphine.event_management.controllers;

import com.dauphine.event_management.dto.CreateRegistrationRequest;
import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.registration.RegistrationNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Registration;
import com.dauphine.event_management.services.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/registrations")
@Tag(name = "Registration Controller API", description = "Registration-related endpoints")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    @Operation(
            summary = "Get all registrations endpoint",
            description = "Return all registrations that are in the database"
    )
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        return ResponseEntity.ok(registrationService.getAllRegistrations());
    }

    @GetMapping("{registrationId}")
    @Operation(
            summary = "Get a registration by ID endpoint",
            description = "Return a certain registration according to its id."
    )
    public ResponseEntity<Registration> getRegistrationById(@PathVariable UUID registrationId) {
        try {
            final Registration registrationToGet = registrationService.getRegistrationById(registrationId);
            return ResponseEntity.ok(registrationToGet);
        } catch (RegistrationNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-event")
    @Operation(
            summary = "Get registrations by event endpoint",
            description = "Return a list of registrations according to the designated event."
    )
    public ResponseEntity<List<Registration>> getRegistrationsByEventId(@RequestParam UUID eventId) {
        try {
            return ResponseEntity.ok(registrationService.getRegistrationsByEventId(eventId));
        } catch (EventNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-user")
    @Operation(
            summary = "Get registrations by user endpoint",
            description = "Return a list of registrations according to the designated user."
    )
    public ResponseEntity<List<Registration>> getRegistrationsByUserId(@RequestParam UUID userId) {
        try {
            return ResponseEntity.ok(registrationService.getRegistrationsByUserId(userId));
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a new registration endpoint",
            description = "Create a new registration with all its data."
    )
    public ResponseEntity<Registration> createRegistration(@RequestBody CreateRegistrationRequest registrationToCreate) {
        try {
            Registration registration = registrationService.createRegistration(registrationToCreate.getUserId(),
                    registrationToCreate.getEventId());
            return ResponseEntity
                    .created(URI.create("v1/registrations/" + registration.getId()))
                    .body(registration);
        } catch (EventNotFoundByIdException | UserNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{registrationId}")
    @Operation(
            summary = "Update a registration endpoint",
            description = "Update a registration according to the id."
    )
    public ResponseEntity<Registration> updateRegistration(@PathVariable UUID registrationId,
                                                    @RequestBody CreateRegistrationRequest registrationToUpdate) {
        try {
            Registration registration = registrationService.updateRegistration(registrationId,
                    registrationToUpdate.getUserId(), registrationToUpdate.getEventId());
            return ResponseEntity.ok(registration);
        } catch (RegistrationNotFoundByIdException | UserNotFoundByIdException | EventNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{registrationId}")
    @Operation(
            summary = "Delete a registration endpoint",
            description = "Delete an existing registration according to the id."
    )
    public ResponseEntity<Void> deleteRegistration(@PathVariable UUID registrationId) {
        try {
            registrationService.deleteRegistrationById(registrationId);
            return ResponseEntity.ok().build();
        } catch (RegistrationNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
