package com.shop.util;

import java.sql.*;

public class DBUtil {

    public static final String DBURL = "jdbc:mysql://localhost:3306/db_shop?useUnicode=true&characterEncoding=UTF-8";
    public static final String DBUSER = "root";
    public static final String DBPASS = "123456";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConn(){

        Connection conn = null;

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            conn= DriverManager.getConnection(DBURL, DBUSER,DBPASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
        //关闭连接
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {}
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {}
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {}
        }
    }

    public static void close(PreparedStatement ps, Connection conn){
        //关闭连接

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {}
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {}
        }
    }
}
