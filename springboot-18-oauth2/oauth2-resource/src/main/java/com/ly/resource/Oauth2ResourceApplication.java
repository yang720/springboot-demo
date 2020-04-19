package com.ly.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2、@EnableResourceServer注解开启资源服务
 *
 * 资源服务器使用：
 *   1、启动sso-auth-server
 *   2、启动sso-auth-resource
 *   3、使用oauth2协议获取sso-auth-server上的token，可以使用postman之类的工具。
 *   4、访问token资源服务器，参考 https://blog.csdn.net/qq_19671173/article/details/79748422
 */
@RestController
@EnableResourceServer
@SpringBootApplication
public class Oauth2ResourceApplication {
    private static final Logger log = LoggerFactory.getLogger(Oauth2ResourceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ResourceApplication.class, args);
    }

    // 添加一个测试访问接口
    @GetMapping("/user")
    public Authentication getUser(Authentication authentication) {
        log.info("resource: user {}", authentication);
        return authentication;
    }
}
