package com.dauphine.event_management.services;

import com.dauphine.event_management.exceptions.category.CategoryAlreadyExistsException;
import com.dauphine.event_management.exceptions.category.CategoryNotFoundByIdException;
import com.dauphine.event_management.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Category> getAllCategoriesByName(String categoryName);

    Category getCategoryById(UUID categoryId) throws CategoryNotFoundByIdException;

    Category createCategory(String categoryName) throws CategoryAlreadyExistsException;

    Category updateCategory(UUID categoryId, String newCategoryName)
            throws CategoryNotFoundByIdException, CategoryAlreadyExistsException;

    void deleteCategoryById(UUID categoryId) throws CategoryNotFoundByIdException;
}