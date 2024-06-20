package com.dauphine.event_management.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "userevent")
public class User {
    @Id
    @Column
    private UUID id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String hashed_password;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "created_at")
    private LocalDateTime created_at;

    public User() {}

    public User(String email, String username, String hashed_password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.username = username;
        this.hashed_password = hashed_password;
        this.created_at = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
