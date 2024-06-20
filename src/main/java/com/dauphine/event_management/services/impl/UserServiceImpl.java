package com.dauphine.event_management.services.impl;

import com.dauphine.event_management.exceptions.user.UserEmailAlreadyExistsException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UsernameAlreadyExistsException;
import com.dauphine.event_management.models.User;
import com.dauphine.event_management.repositories.UserRepository;
import com.dauphine.event_management.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    @Override
    public User getUserById(UUID userId) throws UserNotFoundByIdException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException(userId));
    }

    @Override
    public User createUser(String userEmail, String username, String hashedPassword)
            throws UserEmailAlreadyExistsException, UsernameAlreadyExistsException {
        Optional<User> existingUserByEmail = userRepository.findByEmail(userEmail);
        if (existingUserByEmail.isPresent()) throw new UserEmailAlreadyExistsException(userEmail);

        Optional<User> existingUserByUsername = userRepository.findByUsername(username);
        if (existingUserByUsername.isPresent()) throw new UsernameAlreadyExistsException(username);

        User newUser = new User(userEmail, username, hashedPassword);
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(UUID userId, String newUserEmail, String newUsername, String newUserHashedPassword)
            throws UserNotFoundByIdException, UserEmailAlreadyExistsException, UsernameAlreadyExistsException {
        User userToUpdate = getUserById(userId);

        Optional<User> existingUserByEmail = userRepository.findByEmail(newUserEmail);
        if (existingUserByEmail.isPresent() && !existingUserByEmail.get().equals(userToUpdate))
            throw new UserEmailAlreadyExistsException(newUserEmail);

        Optional<User> existingUserByUsername = userRepository.findByUsername(newUsername);
        if (existingUserByUsername.isPresent() && !existingUserByUsername.get().equals(userToUpdate))
            throw new UsernameAlreadyExistsException(newUsername);

        userToUpdate.setEmail(newUserEmail);
        userToUpdate.setUsername(newUsername);
        userToUpdate.setHashed_password(newUserHashedPassword);

        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUserById(UUID userId) throws UserNotFoundByIdException {
        getUserById(userId);
        userRepository.deleteById(userId);
    }
}
