package com.jordy.guitartracker.service;

import com.jordy.guitartracker.dto.user.CreateUserRequest;
import com.jordy.guitartracker.dto.user.UpdateUserRequest;
import com.jordy.guitartracker.dto.user.UserResponse;
import com.jordy.guitartracker.entity.User;
import com.jordy.guitartracker.exception.UserNotFoundException;
import com.jordy.guitartracker.mapper.UserMapper;
import com.jordy.guitartracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUserById(Long id) {
        final User existingUser = getExistingUserById(id);

        return userMapper.toResponse(existingUser);
    }

    public UserResponse createUser(CreateUserRequest newUser) {
        User user = userMapper.toEntity(newUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse updateUser(Long id, UpdateUserRequest updatedUser) {
        final User user = getExistingUserById(id);
        final User updateTo = userMapper.toEntity(updatedUser);

        user.setUsername(updateTo.getUsername());
        user.setEmail(updateTo.getEmail());

        return userMapper.toResponse(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        final User user = getExistingUserById(id);

        userRepository.delete(user);
    }

    private User getExistingUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
