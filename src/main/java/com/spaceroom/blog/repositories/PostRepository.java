package com.spaceroom.blog.repositories;

import com.spaceroom.blog.domain.PostStatus;
import com.spaceroom.blog.domain.entities.Category;
import com.spaceroom.blog.domain.entities.Post;
import com.spaceroom.blog.domain.entities.Tag;
import com.spaceroom.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByStatusAndTags(PostStatus status, Tag tag);
    List<Post> findAllByStatus(PostStatus status);

    List<Post> findAllByAuthorAndStatus(User user, PostStatus status);
}
