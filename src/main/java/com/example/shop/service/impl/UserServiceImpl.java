package com.example.shop.service.impl;

import com.example.shop.entity.User;
import com.example.shop.mapper.UserMapper;
import com.example.shop.service.UserService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> listAll() {
        return userMapper.findAll();
    }
}
