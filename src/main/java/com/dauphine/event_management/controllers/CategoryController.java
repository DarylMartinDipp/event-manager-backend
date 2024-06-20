package com.dauphine.event_management.controllers;

import com.dauphine.event_management.exceptions.category.CategoryAlreadyExistsException;
import com.dauphine.event_management.exceptions.category.CategoryNotFoundByIdException;
import com.dauphine.event_management.models.Category;
import com.dauphine.event_management.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.net.URI;

@RestController
@RequestMapping("v1/categories")
@Tag(name = "Category Controller API", description = "Category-related endpoints.")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(
            summary = "Get all the categories endpoint",
            description = "Return all categories that are in the database."
    )
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) String categoryName) {
        List<Category> categoriesToGet = categoryName == null || categoryName.isBlank() ?
                        categoryService.getAllCategories() : categoryService.getCategoriesByName(categoryName);
        categoriesToGet.sort(Comparator.comparing(Category::getName));
        return ResponseEntity.ok(categoriesToGet);
    }

    @GetMapping("{categoryId}")
    @Operation(
            summary = "Get a category by ID endpoint",
            description = "Return a certain category according to its id."
    )
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID categoryId) {
        try {
            final Category categoryToGet = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(categoryToGet);
        } catch (CategoryNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a category endpoint",
            description = "Create a new category."
    )
    public ResponseEntity<Category> createCategory(@RequestBody String categoryName) {
        try {
            final Category categoryToCreate = categoryService.createCategory(categoryName);
            return ResponseEntity
                    .created(URI.create("v1/categories/" + categoryToCreate.getId()))
                    .body(categoryToCreate);
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{categoryId}")
    @Operation(
            summary = "Update a category endpoint",
            description = "Update an existing category."
    )
    public ResponseEntity<Category> updateCategory(@PathVariable UUID categoryId, @RequestBody String newCategoryName) {
        try {
            Category categoryToUpdate = categoryService.updateCategory(categoryId, newCategoryName);
            return ResponseEntity.ok(categoryToUpdate);
        } catch (CategoryNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{categoryId}")
    @Operation(
            summary = "Delete a category endpoint",
            description = "Delete an existing category."
    )
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok().build();
        } catch (CategoryNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}