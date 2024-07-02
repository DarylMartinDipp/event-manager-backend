package com.dauphine.event_management.repositories;

import com.dauphine.event_management.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query("SELECT e FROM Event e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :eventTitle, '%'))")
    List<Event> findByTitleContainingIgnoreCase(@Param("eventTitle") String eventTitle);

    @Query("SELECT e FROM Event e WHERE e.event_date > :currentDate")
    List<Event> findUpcomingEvents(@Param("currentDate") LocalDateTime currentDate);

    List<Event> findByCity(String eventCity);
    Optional<Event> findByCountry(String eventCountry);
}
