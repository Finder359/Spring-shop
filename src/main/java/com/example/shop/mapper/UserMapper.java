package com.example.shop.mapper;

import com.example.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface UserMapper {
    User findByUsername(String username);
    int update(User user);
    int insert(User user);
    int delete(int id);
    int batchDelete(List<Integer> ids);
    User findById(int id);
    List<User> findAll();
}
