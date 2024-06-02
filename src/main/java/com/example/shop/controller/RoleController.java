package com.example.shop.controller;

import com.example.shop.models.User;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;
    @RequestMapping(name = "/register/user",method = RequestMethod.POST)
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }
}
