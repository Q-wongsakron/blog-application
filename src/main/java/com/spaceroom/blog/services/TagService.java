package com.spaceroom.blog.services;


import com.spaceroom.blog.domain.entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTags(Set<String> tags);
    void deleteTag(UUID id);

    Tag getTagById(UUID id);

    List<Tag> getTagsByIds(Set<UUID> ids);
}
