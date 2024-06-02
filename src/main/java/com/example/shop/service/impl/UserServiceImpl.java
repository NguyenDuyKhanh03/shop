package com.example.shop.service.impl;

import com.example.shop.models.User;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public int login(User user) {
        User user1=userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(user1!=null)
            return 1;
        return 0;
    }
}
