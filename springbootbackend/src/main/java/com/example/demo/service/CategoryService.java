package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // CREATE
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // READ
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // UPDATE
    public Category updateCategory(String id, Category updatedCategory) {

        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setName(updatedCategory.getName());
        existing.setDescription(updatedCategory.getDescription());

        return categoryRepository.save(existing);
    }

    // DELETE
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
