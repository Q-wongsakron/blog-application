package com.spaceroom.blog.domain.dtos;

import com.spaceroom.blog.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private UUID id;
    private String title;
    private String content;

    private AuthorDto author;
    private CategoryDto category;
    private Set<TagDto> tags;
    private Integer readingTime;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private PostStatus postStatus;
}
