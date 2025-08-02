package com.spaceroom.blog.services.impl;

import com.spaceroom.blog.domain.entities.Tag;
import com.spaceroom.blog.repositories.TagRepository;
import com.spaceroom.blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> existingTags = tagRepository.findByNameIn(tagNames);

        Set<String> existingTagNames =  existingTags.stream().map(Tag::getName).collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                        .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build()
                ).toList();

        List<Tag> savedTags = new ArrayList<>();

        if(!newTags.isEmpty()) {
            savedTags = tagRepository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);


        return savedTags;
    }

    @Transactional
    @Override
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresent(
                tag -> {
                    if (!tag.getPosts().isEmpty()) {
                        throw new IllegalStateException("Cannot delete tag with posts");
                    }
                    tagRepository.deleteById(id);
                }
        );
    }

    @Override
    public Tag getTagById(UUID id) {
        return tagRepository.findById(id).orElseThrow(
                () ->  new EntityNotFoundException("Tag not found with id" + id)
        );
    }

    @Override
    public List<Tag> getTagsByIds(Set<UUID> ids) {
        // Validate that the provided IDs are not empty
        List<Tag> foundTags = tagRepository.findAllById(ids);
        if(foundTags.size() != ids.size()) {
            throw new EntityNotFoundException("Not all tags found for the provided IDs");
        }
        return foundTags;
    }
}
