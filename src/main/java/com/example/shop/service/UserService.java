package com.example.shop.service;

import com.example.shop.models.User;
import com.example.shop.models.dto.UserDto;

public interface UserService {
    void createUser(User user);

    void addUser(User user);

    int login(User user);

    void updateUser(String username, User user);

    UserDto getUser(String username);
}
