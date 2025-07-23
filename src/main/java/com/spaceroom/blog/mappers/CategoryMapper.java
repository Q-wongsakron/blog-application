package com.spaceroom.blog.mappers;

import com.spaceroom.blog.domain.PostStatus;
import com.spaceroom.blog.domain.dtos.CategoryDto;
import com.spaceroom.blog.domain.dtos.CreateCategoryRequest;
import com.spaceroom.blog.domain.entities.Category;
import com.spaceroom.blog.domain.entities.Post;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    // method to convert CreateCategoryRequest to Category entity
    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (posts == null) {
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
