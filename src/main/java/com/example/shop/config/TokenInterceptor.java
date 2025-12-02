package com.example.shop.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.Key;

import static com.example.shop.controller.UserController.SECRET;

public class TokenInterceptor implements HandlerInterceptor {

    private static final String SECRET_KEY = "my-secret-key";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 放行登录接口
        if (request.getRequestURI().contains("/user/login")) {
            return true;
        }

        // 从请求头获取 token
        String token = request.getHeader("Authorization");

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            return false;
        }

        // 支持 Bearer 开头
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            // 校验 token
            Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

//            // 打印用户名（可选）
//            System.out.println("Token 用户：" + claims.getSubject());

        } catch (Exception e) {
            // token 无效 或 过期
            response.setStatus(401);
            return false;
        }

        return true; // 放行
    }
}
