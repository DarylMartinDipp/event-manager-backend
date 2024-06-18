package com.dauphine.event_management.exceptions.category;

import java.util.UUID;

public class CategoryNotFoundByIdException extends Exception {
    public CategoryNotFoundByIdException(UUID categoryId) {
        super("The category designated by id " + categoryId + " was not found.");
    }
}