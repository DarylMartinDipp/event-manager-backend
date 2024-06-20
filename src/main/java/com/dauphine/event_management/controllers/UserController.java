package com.dauphine.event_management.controllers;

import com.dauphine.event_management.dto.CreateUserRequest;
import com.dauphine.event_management.exceptions.user.UserEmailAlreadyExistsException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UsernameAlreadyExistsException;
import com.dauphine.event_management.models.User;
import com.dauphine.event_management.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "User Controller API", description = "User-related endpoints")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(
            summary = "Get all users endpoint",
            description = "Return all users that are in the database, sorted" +
                    "by alphabetical order."
    )
    public ResponseEntity<List<User>> getAllUsers(@RequestParam (required = false) String username) {
        List<User> usersToGet = username == null || username.isBlank() ?
                userService.getAllUsers() : userService.getUsersByUsername(username);
        usersToGet.sort(Comparator.comparing(User::getUsername));
        return ResponseEntity.ok(usersToGet);
    }

    @GetMapping("{userId}")
    @Operation(
            summary = "Get an user by ID endpoint",
            description = "Return a certain user according to its id."
    )
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        try {
            final User userToGet = userService.getUserById(userId);
            return ResponseEntity.ok(userToGet);
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a new user endpoint",
            description = "Create a new user with all its data."
    )
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest userToCreate) {
        try {
            User user = userService.createUser(
                    userToCreate.getUserEmail(), userToCreate.getUsername(), userToCreate.getHashedPassword()
            );
            return ResponseEntity
                    .created(URI.create("v1/users/" + user.getId()))
                    .body(user);
        } catch (UserEmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{userId}")
    @Operation(
            summary = "Update an user endpoint",
            description = "Update an user according to the id."
    )
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody CreateUserRequest userToUpdate) {
        try {
            User user = userService.updateUser(
                    userId, userToUpdate.getUserEmail(), userToUpdate.getUsername(), userToUpdate.getHashedPassword()
            );
            return ResponseEntity.ok(user);
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (UserEmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{userId}")
    @Operation(
            summary = "Delete an user endpoint",
            description = "Delete an existing user according to the id."
    )
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
