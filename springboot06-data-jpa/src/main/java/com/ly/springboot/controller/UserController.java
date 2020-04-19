package com.ly.springboot.controller;

import com.ly.springboot.entity.User;
import com.ly.springboot.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuyang
 * @date 2020/3/6 18:02
 */

@RestController
public class UserController {

    @Resource
    UserRepository userRepository;

    @GetMapping("user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = userRepository.findById(id).get();
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }
}
