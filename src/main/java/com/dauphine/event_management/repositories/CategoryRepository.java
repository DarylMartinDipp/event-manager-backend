package com.dauphine.event_management.repositories;

import com.dauphine.event_management.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("""
        SELECT c
        FROM Category c
        WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%'))
    """)
    List<Category> findAllByName(@Param("name") String categoryName);
}