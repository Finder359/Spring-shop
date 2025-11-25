package com.example.shop.mapper;

import com.example.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface UserMapper {
    User findByUsername(String username);

    int insert(User user);

    List<User> findAll();
}
