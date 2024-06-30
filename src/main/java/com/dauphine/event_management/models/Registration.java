package com.dauphine.event_management.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @Column
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event_id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "registered_at")
    private LocalDateTime registered_at;

    public Registration() {}

    public Registration(User user_id, Event event_id) {
        this.id = UUID.randomUUID();
        this.user_id = user_id;
        this.event_id = event_id;
        this.registered_at = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Event getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Event event_id) {
        this.event_id = event_id;
    }

    public LocalDateTime getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(LocalDateTime registered_at) {
        this.registered_at = registered_at;
    }
}
