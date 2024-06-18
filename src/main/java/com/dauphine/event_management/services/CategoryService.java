package com.dauphine.event_management.services;

import com.dauphine.event_management.exceptions.CategoryAlreadyExistsException;
import com.dauphine.event_management.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_management.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    List<Category> getAllByName(String name);
    Category getById(UUID id) throws CategoryNotFoundByIdException;
    Category create(String name) throws CategoryAlreadyExistsException;
    Category updateName(UUID id, String newName) throws CategoryNotFoundByIdException, CategoryAlreadyExistsException;
    void deleteById(UUID id) throws CategoryNotFoundByIdException;
}
