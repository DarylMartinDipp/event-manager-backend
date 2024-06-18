package com.dauphine.event_management.services.impl;

import com.dauphine.event_management.exceptions.category.CategoryAlreadyExistsException;
import com.dauphine.event_management.exceptions.category.CategoryNotFoundByIdException;
import com.dauphine.event_management.models.Category;
import com.dauphine.event_management.repositories.CategoryRepository;
import com.dauphine.event_management.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllCategoriesByName(String categoryName) {
        return categoryRepository.findAllByName(categoryName);
    }

    @Override
    public Category getCategoryById(UUID categoryId) throws CategoryNotFoundByIdException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundByIdException(categoryId));
    }

    @Override
    public Category createCategory(String categoryName) throws CategoryAlreadyExistsException {
        if (!categoryRepository.findAllByName(categoryName).isEmpty())
            throw new CategoryAlreadyExistsException(categoryName);
        Category category = new Category(UUID.randomUUID(), categoryName);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID categoryId, String newCategoryName)
            throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        Category category = getCategoryById(categoryId);
        if (!categoryRepository.findAllByName(newCategoryName).isEmpty())
            throw new CategoryAlreadyExistsException(newCategoryName);
        category.setName(newCategoryName);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID categoryId) throws CategoryNotFoundByIdException {
        getCategoryById(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}