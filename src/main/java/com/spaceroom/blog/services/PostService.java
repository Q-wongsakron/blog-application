package com.spaceroom.blog.services;


import com.spaceroom.blog.domain.CreatePostRequest;
import com.spaceroom.blog.domain.UpdatePostRequest;
import com.spaceroom.blog.domain.entities.Post;
import com.spaceroom.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID id);

    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    // Why it not use user for authorization => because it is not needed, the post will be created by the user who is logged in
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);

    void deletePost(UUID id);
}
