package com.tm.j10.service;

import com.tm.j10.domain.Category;
import com.tm.j10.domain.Product;
import com.tm.j10.domain.Product;

import com.tm.j10.repository.CategoryRepository;
import jdk.dynalink.linker.LinkerServices;
import com.tm.j10.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategory() {
        return this.categoryRepository.findAllCategoryByIsEnableIsTrue();
    }

    public Optional<Category> getAllCategorySlug(String slug){
        return this.categoryRepository.findBySlug(slug);
    }

    public Optional<Category> getCategoryById(Long id) {
        var ret = this.categoryRepository.findById(id);
        if (ret.isEmpty()) {
            throw new RuntimeException("Category is illegal!");
        }
        return ret;
    }

    public List<Category> getAllCategoryAdmin() {
        return this.categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        if (this.categoryRepository.findById(category.getId()).isPresent()) {
            throw new RuntimeException("Category already exists!");
        }
        return this.categoryRepository.save(category);
    }

    public Boolean updateCategory(Long id, Category updateCategory) {
        var ret = this.categoryRepository.findById(id);
        if (ret.isPresent()) {
            ret.get().setCategoryName(updateCategory.getCategoryName());
            ret.get().setDescription(updateCategory.getDescription());
            ret.get().setSlug(updateCategory.getSlug());
            ret.get().setIsEnable(true);
            this.categoryRepository.save(ret.get());
            return true;
        } else {
            throw new RuntimeException("Category not found!");
        }
    }

    public void deleteCategoryById(Long id, Category category) {
        var ret = this.categoryRepository.findById(id);
        if (ret.isEmpty()) {
            throw new RuntimeException("Category is illegal!");
        }
        if (ret.get().getIsEnable()) {
            ret.get().setIsEnable(false);
            this.categoryRepository.save(ret.get());
        }

    }
}
