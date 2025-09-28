package com.example.board.repository;

import com.example.board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByIsActiveTrueOrderBySortOrderAscNameAsc();

    Optional<Category> findByNameAndIsActiveTrue(String name);

    boolean existsByName(String name);

    @Query("SELECT c FROM Category c WHERE c.name LIKE %:keyword% AND c.isActive = true")
    List<Category> findActiveCategoriesByNameContaining(@Param("keyword") String keyword);

    @Query("SELECT c FROM Category c LEFT JOIN c.posts p WHERE c.isActive = true " +
           "GROUP BY c ORDER BY COUNT(p) DESC, c.sortOrder ASC")
    List<Category> findActiveCategoriesOrderByPostCountDesc();

    @Query("SELECT MAX(c.sortOrder) FROM Category c WHERE c.isActive = true")
    Integer findMaxSortOrder();
}