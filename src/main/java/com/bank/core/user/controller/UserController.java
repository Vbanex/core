package com.bank.core.user.controller;
import com.bank.core.user.dto.CreateUserRequest;
import com.bank.core.user.entity.User;
import com.bank.core.user.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {
    private final UserService service;

    public UserController(UserService repo){
        this.service = repo;
    }

    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody CreateUserRequest request){
        return service.createUser(request);
    }

}
