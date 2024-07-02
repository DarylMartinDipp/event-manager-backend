package com.dauphine.event_management.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @Column
    private UUID id;

    @Column
    private String feedback;

    @Column
    private short rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private User event_id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "created_at")
    private LocalDateTime created_at;

    public Feedback() {}

    public Feedback(String feedback, short rating, User user_id, User event_id) {
        this.id = UUID.randomUUID();
        this.feedback = feedback;
        this.rating = rating;
        this.user_id = user_id;
        this.event_id = event_id;
        this.created_at = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public User getEvent_id() {
        return event_id;
    }

    public void setEvent_id(User event_id) {
        this.event_id = event_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
