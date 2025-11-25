<%@ page import="com.shop.dao.UserDao" %>
<%@ page import="com.shop.dao.impl.UserDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 2025/11/8
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username=request.getParameter("username");
    String password=request.getParameter("password");
    UserDao userDao = new UserDaoImpl();
    boolean success=userDao.login(username,password);

    if (success){

        session.setAttribute("username", username);  // 存用户名
        session.setAttribute("loginTime", new java.util.Date().toString()); // 记录本次登录时间
//        session.setMaxInactiveInterval(120); // 设置 session 超时时间为 120 秒（2分钟）

        response.sendRedirect("index.html");
    } else {
        response.sendRedirect("fail.html");
//        out.println("<script>alert('用户名或密码错误');location.href='login.jsp';</script>");
    }
%>

</body>
</html>
