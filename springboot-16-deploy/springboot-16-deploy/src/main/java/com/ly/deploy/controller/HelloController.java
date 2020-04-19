package com.ly.deploy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 引入spring-boot-devtools 热部署jar包
 * 修改代码后按 Ctrl+F9 即[Build Project/锤子] 可实现热部署，无需重启应用
 * 对前端模板一样有效
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
