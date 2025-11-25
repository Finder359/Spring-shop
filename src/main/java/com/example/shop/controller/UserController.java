package com.example.shop.controller;

import com.example.shop.entity.User;
import com.example.shop.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestParam String username,
                      @RequestParam String password) {
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public int register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.listAll();
    }
}
