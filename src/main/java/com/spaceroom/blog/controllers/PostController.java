package com.spaceroom.blog.controllers;

import com.spaceroom.blog.domain.dtos.PostDto;
import com.spaceroom.blog.domain.entities.Post;
import com.spaceroom.blog.domain.entities.User;
import com.spaceroom.blog.mappers.PostMapper;
import com.spaceroom.blog.services.PostService;
import com.spaceroom.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false)UUID categoryId,
            @RequestParam(required = false)UUID tagId
            ){

        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId){
        // Retrieve the currently logged-in user by userId
        User loggedInUser = userService.getUserById(userId);

        // Fetch draft posts for the user
        List<Post> draftPost = postService.getDraftPosts(loggedInUser);

        // Map Post entities to PostDto objects
        List<PostDto> postDtos = draftPost.stream().map(postMapper::toDto).toList();

        // Return the list of PostDto objects in the response
        return ResponseEntity.ok(postDtos);
    }
}
