package com.ly.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 3、@EnableOAuth2Sso注解配置sso服务
 *
 * 单点登录使用方式：
 *   1、启动sso-auth-server
 *   2、启动sso-auth-client
 *   3、访问 http://127.0.0.1:8085/user
 *
 *   注意：
 *    单点登录在域名相同时浏览器会使用同一个cookie导致页面一直再login页重定向，
 *    所以需要把认证服务器和sso客户端设置为不同域名下启动
 */

@SpringBootApplication
public class Oauth2ClientApplication {
    private static final Logger log = LoggerFactory.getLogger(Oauth2ClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ClientApplication.class, args);
    }

}
