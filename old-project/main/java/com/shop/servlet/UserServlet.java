package com.shop.servlet;

import com.shop.dao.UserDao;
import com.shop.dao.impl.UserDaoImpl;
import com.shop.entity.User;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserServlet", value = "/admin/UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String op=request.getParameter("op");

        if("login".equals(op)) {
            try {
                doLogin(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        //查询所有用户
        if ("queryAll".equals(op)){
            try {
                doQueryAll(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        if ("add".equals(op)){
                doAdd(request,response);
        }

        if ("delete".equals(op)){
            doDelete(request,response);
        }

        if("batchDelete".equals(op)){
            doBatchDelete(request, response);
        }

        if ("showEdit".equals(op)) {
            doShowEdit(request, response);
        }

        if ("update".equals(op)) {
            doUpdate(request, response);
        }



    }

    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //登录
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        boolean success = userDao.login(username, password);

        if (success) {

            session.setAttribute("username", username);  // 存用户名
            session.setAttribute("loginTime", new java.util.Date().toString()); // 记录本次登录时间
//        session.setMaxInactiveInterval(120); // 设置 session 超时时间为 120 秒（2分钟）

//            response.sendRedirect("index.html");
            try {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("fail.html");
//        out.println("<script>alert('用户名或密码错误');location.href='login.jsp';</script>");
        }
    }

    public void doQueryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao userDao = new UserDaoImpl();

        // ① 获取当前页码
        String page = request.getParameter("page");
        int currentPage = (page == null ? 1 : Integer.parseInt(page));

        // ② 查询分页数据
        ArrayList<User> users = userDao.queryPage(currentPage);

        // ③ 查询总页数
        int pageCount = userDao.getPageCount();

        // ④ 回传 JSP
        request.setAttribute("users", users);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageCount", pageCount);

        request.getRequestDispatcher("ListUser.jsp").forward(request, response);
    }


    public  void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        UserDao userDao = new UserDaoImpl();
        int n=userDao.add(user);
        if(n>0){
            response.sendRedirect("success.jsp");
        }else {
            response.sendRedirect("error.jsp");
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id=0;

        String tid=request.getParameter("id");
        if(tid!=null&&!tid.isEmpty()){
            id=Integer.parseInt(tid);
        }

        UserDao userDao = new UserDaoImpl();
        int n=userDao.delete(id);
        if(n>0){
            response.sendRedirect("success.jsp");
        }else {
            response.sendRedirect("error.jsp");
        }
    }

    public void doBatchDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String[] ids = request.getParameterValues("id[]");

        if(ids == null || ids.length == 0){
            response.sendRedirect("error.jsp");
            return;
        }

        UserDao userDao = new UserDaoImpl();
        int successCount = 0;

        for (String idStr : ids){
            if(idStr == null || idStr.trim().equals("")) continue;  // 跳过空值
            int id = Integer.parseInt(idStr);
            int n = userDao.delete(id);
            if(n > 0){
                successCount++;
            }
        }

        if(successCount == ids.length){
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    public void doShowEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tid = request.getParameter("id");
        int id = Integer.parseInt(tid);

        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(id);

        request.setAttribute("user", user);
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);

        UserDao userDao = new UserDaoImpl();
        int n = userDao.update(user);

        if(n > 0){
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }





}