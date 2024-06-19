package com.dauphine.event_management.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column
    private UUID id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private short number_street;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String country;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column
    private LocalDateTime event_date;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer_id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category_id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "created_at")
    private LocalDateTime created_at;

    public Event() {}

    public Event(String title, String description, short number_street, String street, String city,
                 String country,LocalDateTime event_date, User organizer_id, Category category_id) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.number_street = number_street;
        this.street = street;
        this.city = city;
        this.country = country;
        this.event_date = event_date;
        this.organizer_id = organizer_id;
        this.category_id = category_id;
        this.created_at = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getNumber_street() {
        return number_street;
    }

    public void setNumber_street(short number_street) {
        this.number_street = number_street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDateTime event_date) {
        this.event_date = event_date;
    }

    public User getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(User organizer_id) {
        this.organizer_id = organizer_id;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
