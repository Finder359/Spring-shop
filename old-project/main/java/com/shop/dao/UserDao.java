package com.shop.dao;

import com.shop.entity.User;

import java.util.ArrayList;

public interface UserDao {
    boolean login(String username,String password);
    ArrayList queryAll();
    int add(User user);
    int delete(int id);
    int getRecordCount();
    int getPageCount();
    ArrayList queryPage(int Cpage);
    User findById(int id);
    int update(User  user);
}
