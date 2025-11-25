package com.shop.test;

import com.shop.dao.UserDao;
import com.shop.dao.impl.UserDaoImpl;
import com.shop.entity.User;

import java.util.ArrayList;


public class TestUserDao {
    public static void main(String[] args) {
//    testLogin();
//        testAdd();
//        testDelete();
//        testGetRecordCount();
        testQueryPage();
    }

    public static void testLogin(){
        UserDao userDao = new UserDaoImpl();
        boolean flag = userDao.login("admin","123456");
        System.out.println(flag);
    }

    public static void testAdd(){
        UserDao userDao = new UserDaoImpl();
        User user=new User();
        user.setUsername("abc");
        user.setPassword("123456");
        int n= userDao.add(user);
        System.out.println(n);
    }

    public static void testDelete(){
        UserDao userDao = new UserDaoImpl();
        int n= userDao.delete(8);
        System.out.println(n);

    }

    public static void testGetRecordCount(){
        UserDao userDao = new UserDaoImpl();
        int n= userDao.getRecordCount();
        System.out.println(n);

    }

    public static void testQueryPage(){
        UserDao userDao = new UserDaoImpl();
        ArrayList<User> users=userDao.queryPage(1);
        for(User user:users){
            System.out.println(user.toString());
        }
    }
}
