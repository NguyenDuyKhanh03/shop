package com.example.shop.service.impl;

import com.example.shop.models.User;
import com.example.shop.models.dto.UserDto;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
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

    @Override
    public void updateUser(String username, User user) {
        Optional<User> user1=userRepository.findByUsername(username);
        if(user1.isEmpty()){
            throw new RuntimeException();
        }
        else{
            User user2=user1.get();
            if(!user.getEmail().isEmpty())
                user2.setEmail(user.getEmail());
            if(!user.getNumberPhone().isEmpty())
                user2.setNumberPhone(user.getNumberPhone());
            if(!user.getAddress().isEmpty())
                user2.setAddress(user.getAddress());
            userRepository.save(user2);
        }
    }

    @Override
    public UserDto getUser(String username) {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isEmpty())
            throw new RuntimeException();

        return modelMapper.map(user.get(),UserDto.class);
    }
}
