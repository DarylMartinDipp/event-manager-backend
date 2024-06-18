package com.dauphine.event_management.exceptions.category;

public class CategoryAlreadyExistsException extends Exception {
    public CategoryAlreadyExistsException (String categoryName) {
        super("The category named " + categoryName + " already exists.");
    }
}