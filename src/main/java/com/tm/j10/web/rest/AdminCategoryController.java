package com.tm.j10.web.rest;

import com.tm.j10.domain.Category;
import com.tm.j10.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin")
public class AdminCategoryController {
    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategoryAdmin() {
        var ret = this.categoryService.getAllCategoryAdmin();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategoryByIdAdmin(@PathVariable("id") Long id) {
        var ret = this.categoryService.getCategoryById(id);
        return ResponseEntity.ok(ret);
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category newCategory) {
        var ret = this.categoryService.createCategory(newCategory);
        return ResponseEntity.created(URI.create("/")).body(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable("id") Long id, @RequestBody Category updateCategory) {
        var ret = this.categoryService.updateCategory(id, updateCategory);
        if (ret) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long id, @RequestBody Category category) {
        this.categoryService.deleteCategoryById(id, category);
        return ResponseEntity.ok().build();
    }
}
