package com.dauphine.event_management.services;

import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.registration.RegistrationNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Registration;

import java.util.List;
import java.util.UUID;

public interface RegistrationService {
    List<Registration> getAllRegistrations();

    Registration getRegistrationById(UUID registrationId) throws RegistrationNotFoundByIdException;

    List<Registration> getRegistrationsByEventId(UUID eventId) throws EventNotFoundByIdException;

    List<Registration> getRegistrationsByUserId(UUID userId) throws UserNotFoundByIdException;

    Registration createRegistration(UUID userId, UUID eventId) throws UserNotFoundByIdException,
            EventNotFoundByIdException;

    Registration updateRegistration(UUID registrationId, UUID userId, UUID eventId)
            throws RegistrationNotFoundByIdException, UserNotFoundByIdException, EventNotFoundByIdException;

    void deleteRegistrationById(UUID registrationId) throws RegistrationNotFoundByIdException;
}
