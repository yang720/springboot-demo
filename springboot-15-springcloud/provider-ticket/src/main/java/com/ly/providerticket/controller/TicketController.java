package com.ly.providerticket.controller;

import com.ly.providerticket.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuyang
 * @date 2020/3/14 21:30
 */
@RestController
public class TicketController {

    @Resource
    TicketService ticketService;

    @GetMapping("/ticket")
    public String getTicket(){
        return ticketService.getTicket();
    }
}
