package com.spaceroom.blog.services;

import com.spaceroom.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
