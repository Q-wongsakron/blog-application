package com.spaceroom.blog.controllers;

import com.spaceroom.blog.domain.dtos.CreateTagsRequest;
import com.spaceroom.blog.domain.dtos.TagDto;
import com.spaceroom.blog.domain.entities.Tag;
import com.spaceroom.blog.mappers.TagMapper;
import com.spaceroom.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<Tag> tags = tagService.getTags();

        List<TagDto> tagResponse = tags.stream().map(tagMapper::toTagResponse).toList();
        return ResponseEntity.ok(tagResponse);
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {

       List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
       List<TagDto> createdTagResponse = savedTags.stream().map(
               tagMapper::toTagResponse
       ).toList();
       return new ResponseEntity<>(
               createdTagResponse,
               HttpStatus.CREATED
       );

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
