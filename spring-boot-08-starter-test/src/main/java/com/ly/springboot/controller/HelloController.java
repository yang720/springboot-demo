package com.ly.springboot.controller;

import com.ly.starter.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuyang
 * @date 2020/3/6 20:52
 */

@RestController
public class HelloController {

    @Resource
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.sayHello("自定义starter生效");
    }
}
