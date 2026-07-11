package com.jordy.guitartracker.mapper;

import com.jordy.guitartracker.dto.user.CreateUserRequest;
import com.jordy.guitartracker.dto.user.UpdateUserRequest;
import com.jordy.guitartracker.dto.user.UserResponse;
import com.jordy.guitartracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(CreateUserRequest createUserRequest) {
        final User newUser = new User();

        newUser.setUsername(createUserRequest.username());
        newUser.setEmail(createUserRequest.email());
        newUser.setPassword(createUserRequest.password());

        return newUser;
    }

    public User toEntity(UpdateUserRequest updateUserRequest) {
        final User updatedUser = new User();

        updatedUser.setUsername(updateUserRequest.username());
        updatedUser.setEmail(updateUserRequest.email());

        return updatedUser;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}
