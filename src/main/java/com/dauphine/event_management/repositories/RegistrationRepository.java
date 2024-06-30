package com.dauphine.event_management.repositories;

import com.dauphine.event_management.models.Event;
import com.dauphine.event_management.models.Registration;
import com.dauphine.event_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    @Query("SELECT r FROM Registration r WHERE r.event_id = :eventId")
    List<Registration> findByEventId(Event eventId);

    @Query("SELECT r FROM Registration r WHERE r.user_id = :userId")
    List<Registration> findByUserId(User userId);
}
