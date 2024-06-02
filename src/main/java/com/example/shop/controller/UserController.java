package com.example.shop.controller;

import com.example.shop.models.User;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/login")
    public int login(@RequestParam String username, @RequestParam String password){
        User user = new User(username, password);
        return userService.login(user);
    }
}
