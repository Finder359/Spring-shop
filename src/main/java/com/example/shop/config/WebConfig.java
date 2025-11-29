package com.example.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/user/**")      // 所有后端接口都要校验
                .excludePathPatterns("/user/login"); // 登录接口放行
    }
}
