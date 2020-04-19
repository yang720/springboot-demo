package com.ly.consumeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author liuyang
 * @date 2020/3/14 21:53
 */
@RestController
public class UserController {

    @Resource
    RestTemplate restTemplate;

    @GetMapping("/buy")
    public String byTicket(String name){
        String ticket = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return name + "购买了" + ticket;
    }
}
