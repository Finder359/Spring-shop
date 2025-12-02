package com.example.shop;

import com.example.shop.entity.User;
import com.example.shop.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;


    @Test
    void testFindAll() {
        List<User> users = userMapper.findAll();
        System.out.println("查询到的用户数量：" + users.size());
        for (User user : users) {
            System.out.println(user.getId() + " - " + user.getUsername()+ " - " +user.getPassword());
        }
    }
    
    @Test
    void testSQL(){
        long t = System.currentTimeMillis();
        List<User> users = userMapper.findAll();
        System.out.println("执行时间：" + (System.currentTimeMillis() - t) + "ms");

    }
}
