package com.spaceroom.blog.controllers;


import com.spaceroom.blog.domain.dtos.CategoryDto;
import com.spaceroom.blog.domain.dtos.CreateCategoryRequest;
import com.spaceroom.blog.domain.entities.Category;
import com.spaceroom.blog.mappers.CategoryMapper;
import com.spaceroom.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryService.listCategories()
                .stream().map(categoryMapper::toDto)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            // @Valid @RequestBody createCategoryRequest is used to validate the incoming request body
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

        // converts the CreateCategoryRequest to a Category entity
        Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);
        // calls the service to create the category
        Category savedCategory = categoryService.createCategory(categoryToCreate);
        return new ResponseEntity<>(
                categoryMapper.toDto(savedCategory),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content response
    }
}
