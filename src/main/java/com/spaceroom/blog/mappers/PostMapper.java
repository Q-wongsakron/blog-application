package com.spaceroom.blog.mappers;

import com.spaceroom.blog.domain.CreatePostRequest;
import com.spaceroom.blog.domain.UpdatePostRequest;
import com.spaceroom.blog.domain.dtos.CreatePostRequestDto;
import com.spaceroom.blog.domain.dtos.PostDto;
import com.spaceroom.blog.domain.dtos.UpdatePostRequestDto;
import com.spaceroom.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "status", source = "status")
    PostDto toDto(Post post);

    // Mapping for CreatePostRequestDto to CreatePostRequest
    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    // Mapping for UpdatePostRequestDto to UpdatePostRequest
    // it is mapped from dto to entity
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);
}
