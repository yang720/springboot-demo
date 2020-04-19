package com.ly.user.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ly.ticket.service.TicketService;
import org.springframework.stereotype.Service;

/**
 * @author liuyang
 * @date 2020/3/14 13:35
 */
@Service
public class UserService {

    @Reference
    private TicketService ticketService;

    public String hello(){
        String ticket = ticketService.getTicket();
        return "买到票了："+ ticket;
    }
}
