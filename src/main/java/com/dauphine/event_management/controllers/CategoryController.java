package com.dauphine.event_management.controllers;

import com.dauphine.event_management.exceptions.CategoryAlreadyExistsException;
import com.dauphine.event_management.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_management.models.Category;
import com.dauphine.event_management.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.net.URI;

@RestController
@RequestMapping("v1/categories")
@Tag(name = "Category Controller API", description = "Category-related endpoints.")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Get all the categories endpoint",
            description = "Return all the categories that are in the database."
    )
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name) {
        List<Category> categoriesToGet =
                name == null || name.isBlank() ? service.getAll() : service.getAllByName(name);
        return ResponseEntity.ok(categoriesToGet);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Retrieve by ID endpoint",
            description = "Return a certain category according to its id."
    )
    public ResponseEntity<Category> retrieveCategoryById(@PathVariable UUID id) {
        try {
            final Category category = service.getById(id);
            return ResponseEntity.ok(category);
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
            final Category categoryToCreate = service.create(categoryName);
            return ResponseEntity
                    .created(URI.create("v1/categories/" + categoryToCreate.getId()))
                    .body(categoryToCreate);
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update a category endpoint",
            description = "Update an existing category."
    )
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody String name) {
        try {
            Category categoryToUpdate = service.updateName(id, name);
            return ResponseEntity.ok(categoryToUpdate);
        } catch (CategoryNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a category endpoint",
            description = "Delete an existing category."
    )
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (CategoryNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}