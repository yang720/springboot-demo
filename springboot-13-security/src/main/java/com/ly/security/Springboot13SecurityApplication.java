package com.ly.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、引入SpringSecurity
 * 2、编写SpringSecurity配置类SecurityConfig extends WebSecurityConfigurerAdapter 加上 @EnableWebSecurity 注解
 * 3、控制访问权限
 *
 */
@SpringBootApplication
public class Springboot13SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot13SecurityApplication.class, args);
    }

}
