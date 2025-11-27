package com.example.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")   // 允许所有前端域名
                        .allowedMethods("*")          // 允许所有方法：GET POST PUT DELETE
                        .allowedHeaders("*")          // 允许所有请求头
                        .allowCredentials(true)       // 允许发送 cookie
                        .maxAge(3600);
            }
        };
    }
}
