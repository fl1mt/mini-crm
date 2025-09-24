package com.example.minicrm.controller;

import com.example.minicrm.entity.UserEntity;
import com.example.minicrm.service.UserService;
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
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity user) {
        return userService.register(user);
    }
}
