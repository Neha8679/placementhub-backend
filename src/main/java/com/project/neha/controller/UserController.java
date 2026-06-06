package com.project.neha.controller;

import com.project.neha.dto.LoginRequest;
import com.project.neha.dto.UserRequest;
import com.project.neha.dto.UserResponse;
import com.project.neha.entity.User;
import com.project.neha.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.save(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id){

        System.out.println("GET USER BY ID CALLED");

        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable UUID id,
            @RequestBody User user) {

        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }
    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request
    ) {
        return userService.login(request);
    }
}

