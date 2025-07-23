package com.spaceroom.blog.repositories;

import com.spaceroom.blog.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    // Find all categories with the count of posts in each category
    @Query("SELECT c FROM Category c LEFT JOIN c.posts")
    List<Category> findAllWithPostCount();
}
