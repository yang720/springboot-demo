package com.ly.user.controller;

import com.ly.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuyang
 * @date 2020/3/14 17:31
 */
@RestController
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/hello")
    public String hello(){
        String hello = userService.hello();
        return hello;
    }
}
