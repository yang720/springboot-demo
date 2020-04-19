package com.ly.providerticket.service;

import org.springframework.stereotype.Service;

/**
 * @author liuyang
 * @date 2020/3/14 21:27
 */
@Service
public class TicketService {

    public String getTicket(){
        System.out.println("8001");
        return "《你的名字》";
    }
}
