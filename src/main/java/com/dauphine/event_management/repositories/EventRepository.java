package com.dauphine.event_management.repositories;

import com.dauphine.event_management.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByTitleContaining(String eventTitle);
    Optional<Event> findByCity(String eventCity);
    Optional<Event> findByCountry(String eventCountry);
}
