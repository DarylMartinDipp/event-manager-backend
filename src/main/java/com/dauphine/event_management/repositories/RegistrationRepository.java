package com.dauphine.event_management.repositories;

import com.dauphine.event_management.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    @Query("SELECT r FROM Registration r WHERE r.event_id = :eventId")
    List<Registration> findByEventId(UUID eventId);

    @Query("SELECT r FROM Registration r WHERE r.user_id = :userId")
    List<Registration> findByUserId(UUID userId);
}
