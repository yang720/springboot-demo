package com.ly.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyang
 * @date 2020/3/9 18:53
 */
@Configuration
public class MyAMQPConfig {

    /**
     * 自定义发送的序列化格式
     * @return MessageConverter 消息格式转换
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
