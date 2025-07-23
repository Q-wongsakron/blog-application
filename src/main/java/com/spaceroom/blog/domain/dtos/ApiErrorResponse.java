package com.spaceroom.blog.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// This class represents the structure of an API error response.
public class ApiErrorResponse {


    private int status;
    private String message;
    private List<FieldError> errors;

    // Nested class to represent individual field errors
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FieldError {
        private String field;
        private String message;
    }
}
