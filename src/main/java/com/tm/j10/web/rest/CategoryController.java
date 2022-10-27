package com.tm.j10.web.rest;

import com.tm.j10.domain.Category;
import com.tm.j10.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("")
    public ResponseEntity<List<Category>> getAllCategory() {
        var ret = this.categoryService.getAllCategory();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/slug")
    public ResponseEntity<Category> getCategorieSlug(@RequestParam("slug") String slug) {
        var ret = this.categoryService.getAllCategorySlug(slug);
        return ResponseEntity.ok(ret.get());
    }
}
