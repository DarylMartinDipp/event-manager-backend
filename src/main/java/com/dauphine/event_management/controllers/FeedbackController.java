package com.dauphine.event_management.controllers;

import com.dauphine.event_management.dto.CreateFeedbackRequest;
import com.dauphine.event_management.exceptions.event.EventNotFoundByIdException;
import com.dauphine.event_management.exceptions.feedback.FeedbackNotFoundByIdException;
import com.dauphine.event_management.exceptions.user.UserNotFoundByIdException;
import com.dauphine.event_management.models.Feedback;
import com.dauphine.event_management.services.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/feedbacks")
@Tag(name = "Feedback Controller API", description = "Feedback-related endpoints")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @Operation(
            summary = "Get all feedbacks endpoint",
            description = "Return all feedbacks that are in the database"
    )
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @GetMapping("{feedbackId}")
    @Operation(
            summary = "Get a feedback by ID endpoint",
            description = "Return a certain feedback according to its id."
    )
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable UUID feedbackId) {
        try {
            final Feedback feedbackToGet = feedbackService.getFeedbackById(feedbackId);
            return ResponseEntity.ok(feedbackToGet);
        } catch (FeedbackNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-event")
    @Operation(
            summary = "Get feedbacks by event endpoint",
            description = "Return a list of feedbacks according to the designated event."
    )
    public ResponseEntity<List<Feedback>> getFeedbacksByEventId(@RequestParam UUID eventId) {
        try {
            return ResponseEntity.ok(feedbackService.getFeedbacksByEventId(eventId));
        } catch (EventNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-user")
    @Operation(
            summary = "Get feedbacks by user endpoint",
            description = "Return a list of feedbacks according to the designated user."
    )
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@RequestParam UUID userId) {
        try {
            return ResponseEntity.ok(feedbackService.getFeedbacksByUserId(userId));
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a new feedback endpoint",
            description = "Create a new feedback with all its data."
    )
    public ResponseEntity<Feedback> createFeedback(@RequestBody CreateFeedbackRequest feedbackToCreate) {
        try {
            Feedback feedback = feedbackService.createFeedback(feedbackToCreate.getFeedback(),
                    feedbackToCreate.getRating(), feedbackToCreate.getUserId(), feedbackToCreate.getEventId());
            return ResponseEntity
                    .created(URI.create("v1/feedback/" + feedback.getId()))
                    .body(feedback);
        } catch (EventNotFoundByIdException | UserNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{feedbackId}")
    @Operation(
            summary = "Update a feedback endpoint",
            description = "Update a feedback according to the id."
    )
    public ResponseEntity<Feedback> updateFeedback(@PathVariable UUID feedbackId,
                                                           @RequestBody CreateFeedbackRequest feedbackToUpdate) {
        try {
            Feedback feedback = feedbackService.updateFeedback(feedbackId, feedbackToUpdate.getFeedback(),
                    feedbackToUpdate.getRating(), feedbackToUpdate.getUserId(), feedbackToUpdate.getEventId());
            return ResponseEntity.ok(feedback);
        } catch (FeedbackNotFoundByIdException | UserNotFoundByIdException | EventNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{feedbackId}")
    @Operation(
            summary = "Delete a feedback endpoint",
            description = "Delete an existing feedback according to the id."
    )
    public ResponseEntity<Void> deleteFeedback(@PathVariable UUID feedbackId) {
        try {
            feedbackService.deleteFeedbackById(feedbackId);
            return ResponseEntity.ok().build();
        } catch (FeedbackNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
