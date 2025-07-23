package com.spaceroom.blog.services.impl;

import com.spaceroom.blog.domain.entities.Category;
import com.spaceroom.blog.repositories.CategoryRepository;
import com.spaceroom.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    // Injecting the CategoryRepository to interact with the database
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    public Category createCategory(Category category) {
        String categoryName = category.getName();

        if (categoryRepository.existsByNameIgnoreCase(categoryName)) {
            throw new IllegalArgumentException("Category already exists with name: " + categoryName);
        }

        return categoryRepository.save(category);
    }
}
