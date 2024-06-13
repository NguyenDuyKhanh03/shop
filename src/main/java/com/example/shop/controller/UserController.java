package com.example.shop.controller;

import com.example.shop.models.User;
import com.example.shop.models.dto.UserDto;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/update/{username}")
    public void updateUser(@PathVariable("username") String username ,@RequestBody User user){
        userService.updateUser(username,user);
    }

    @GetMapping("get/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username){
        UserDto userDto=userService.getUser(username);
        return  ResponseEntity.ok(userDto);
    }
}
