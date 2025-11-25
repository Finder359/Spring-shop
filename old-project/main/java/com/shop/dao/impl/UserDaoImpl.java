package com.shop.dao.impl;

import com.shop.dao.UserDao;
import com.shop.entity.User;
import com.shop.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    public static final int PAGESIZE=6;//分页每页数量

    @Override
    public boolean login(String username, String password) {
        boolean flag = false;
        conn= DBUtil.getConn();
        String sql="select * from admin_info where name=? and pwd=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return flag;
    }

    @Override
    public ArrayList<User> queryAll() {

        ArrayList<User> users = new ArrayList<>();
        conn = DBUtil.getConn();

        String sql="select * from admin_info";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

            //遍历结果集
            while (rs.next()) {
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getNString("name"));
                user.setPassword(rs.getNString("pwd"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return users;
    }

    @Override
    public int add(User user) {
        int n=0;
        conn = DBUtil.getConn();

        try {
            String sql="insert into admin_info(name,pwd) values(?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            n=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(ps,conn);
        }

        return n;
    }

    @Override
    public int delete(int id) {
        int n=0;
        conn = DBUtil.getConn();
        String sql="delete from admin_info where id=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            n=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(ps,conn);
        }

        return n;
    }

    @Override
    public int getRecordCount() {
        int recordCount=0;
        conn= DBUtil.getConn();
        String sql="select count(*) from admin_info ";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                recordCount=rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs,ps,conn);
        }

        return recordCount;
    }

    @Override
    public int getPageCount() {
        return (getRecordCount()+PAGESIZE-1)/PAGESIZE;
    }

    @Override
    public ArrayList queryPage(int Cpage) {

        ArrayList<User> users = new ArrayList<>();
        conn = DBUtil.getConn();

        String sql="select * from admin_info limit ?,?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,(Cpage-1)*PAGESIZE);
            ps.setInt(2,PAGESIZE);
            rs=ps.executeQuery();

            //遍历结果集
            while (rs.next()) {
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getNString("name"));
                user.setPassword(rs.getNString("pwd"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return users;

    }

    @Override
    public User findById(int id) {

        User user = null;
        conn = DBUtil.getConn();

        String sql = "SELECT * FROM admin_info WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getNString("name"));
                user.setPassword(rs.getNString("pwd"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return user;
    }


    @Override
    public int update(User user) {
        conn = DBUtil.getConn();
        String sql = "UPDATE admin_info SET name = ?, pwd = ? WHERE id = ?";
        int n=0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());  // 根据 id 修改对应用户
            n = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(ps, conn);
        }

        return n;
    }


}
