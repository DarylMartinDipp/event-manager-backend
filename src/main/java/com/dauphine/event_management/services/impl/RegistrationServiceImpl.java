package com.dauphine.event_management.services.impl;

import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.registration.RegistrationNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Event;
import com.dauphine.event_management.models.Registration;
import com.dauphine.event_management.models.User;
import com.dauphine.event_management.repositories.RegistrationRepository;
import com.dauphine.event_management.services.EventService;
import com.dauphine.event_management.services.RegistrationService;
import com.dauphine.event_management.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final EventService eventService;
    private final UserService userService;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository, EventService eventService,
                                   UserService userService) {
        this.registrationRepository = registrationRepository;
        this.eventService = eventService;
        this.userService = userService;
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration getRegistrationById(UUID registrationId) throws RegistrationNotFoundByIdException {
        return registrationRepository.findById(registrationId).orElseThrow(
                () -> new RegistrationNotFoundByIdException(registrationId)
        );
    }

    @Override
    public List<Registration> getRegistrationsByEventId(UUID eventId) throws EventNotFoundByIdException {
        Event event = eventService.getEventById(eventId);
        return registrationRepository.findByEventId(event);
    }

    @Override
    public List<Registration> getRegistrationsByUserId(UUID userId) throws UserNotFoundByIdException {
        User user = userService.getUserById(userId);
        return registrationRepository.findByUserId(user);
    }

    @Override
    public Registration createRegistration(UUID userId, UUID eventId) throws UserNotFoundByIdException, EventNotFoundByIdException {
        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId);

        Registration newRegistration = new Registration(user, event);
        return registrationRepository.save(newRegistration);
    }

    @Override
    public Registration updateRegistration(UUID registrationId, UUID userId, UUID eventId) throws RegistrationNotFoundByIdException, UserNotFoundByIdException, EventNotFoundByIdException {
        Registration registrationToUpdate = getRegistrationById(registrationId);

        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId);

        registrationToUpdate.setUser_id(user);
        registrationToUpdate.setEvent_id(event);

        return registrationRepository.save(registrationToUpdate);
    }

    @Override
    public void deleteRegistrationById(UUID registrationId) throws RegistrationNotFoundByIdException {
        getRegistrationById(registrationId);
        registrationRepository.deleteById(registrationId);
    }
}
