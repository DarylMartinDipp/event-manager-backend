package com.dauphine.event_management.repositories;

import com.dauphine.event_management.models.Event;
import com.dauphine.event_management.models.Feedback;
import com.dauphine.event_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
    @Query("SELECT f FROM Feedback f WHERE f.event_id = :eventId")
    List<Feedback> findByEventId(Event eventId);

    @Query("SELECT f FROM Feedback f WHERE f.user_id = :userId")
    List<Feedback> findByUserId(User userId);
}
