package com.shop.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyServlet", value = "/MyServlet")
public class MyServlet extends HttpServlet {
    public MyServlet() {
    super();
        System.out.println("构造方法被调用");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doget被调用了");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("dopost被调用了");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("service方法被调用");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy方法被调用");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init方法被调用");
    }
}