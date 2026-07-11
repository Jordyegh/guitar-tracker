package com.jordy.guitartracker.controller;

import com.jordy.guitartracker.dto.user.CreateUserRequest;
import com.jordy.guitartracker.dto.user.UpdateUserRequest;
import com.jordy.guitartracker.dto.user.UserResponse;
import com.jordy.guitartracker.entity.User;
import com.jordy.guitartracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id,
                                   @Valid @RequestBody UpdateUserRequest user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return "Deleted user " + id;
    }
}
