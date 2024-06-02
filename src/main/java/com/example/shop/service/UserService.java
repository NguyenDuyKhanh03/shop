package com.example.shop.service;

import com.example.shop.models.User;

public interface UserService {
    void createUser(User user);

    void addUser(User user);

    int login(User user);
}
