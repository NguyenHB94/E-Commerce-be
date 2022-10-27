package com.tm.j10.repository;

import com.tm.j10.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllCategoryByIsEnableIsTrue();

    Optional<Category> findBySlug(String slug);

    Optional<Category> findByIdAndIsEnableIsTrue(Long categoryId);
}
