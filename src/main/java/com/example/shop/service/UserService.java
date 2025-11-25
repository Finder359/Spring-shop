package com.example.shop.service;

import com.example.shop.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User login(String username, String password);

    int register(User user);

    List<User> listAll();
}
