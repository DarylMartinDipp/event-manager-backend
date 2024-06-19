package com.dauphine.event_management.services;

import com.dauphine.event_management.exceptions.user.UserEmailAlreadyExistsException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UsernameAlreadyExistsException;
import com.dauphine.event_management.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();

    List<User> getAllUsersByUsername(String username);

    User getUserById(UUID userId) throws UserNotFoundByIdException;

    User createUser(String userEmail, String username, String hashedPassword)
            throws UserEmailAlreadyExistsException, UsernameAlreadyExistsException;

    User updateUser(UUID userId, String newUserEmail, String newUsername, String newUserHashedPassword)
            throws UserNotFoundByIdException, UserEmailAlreadyExistsException, UsernameAlreadyExistsException;

    void deleteUserById(UUID userId) throws UserNotFoundByIdException;
}
