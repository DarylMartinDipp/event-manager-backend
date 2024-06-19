package com.dauphine.event_management.repositories;

import com.dauphine.event_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUsernameContaining(String username);
    Optional<User> findByEmail(String userEmail);
    Optional<User> findByUsername(String username);
}
