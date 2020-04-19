package com.ly.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置类 RabbitAutoConfiguration
 *   1、自动配置连接工厂ConnectionFactory;
 *   2、RabbitProperties 封装了 RabbitMQ的配置
 *   3、RabbitTemplate：给RabbitMQ发送和接收消息；
 *   4、AmqpAdmin：RabbitMQ系统管理功能组件
 *       AmqpAdmin：创建和删除 Queue，Exchange，Binding
 *   5、@EnableRabbit + @RabbitListener 监听消息队列的内容
 *
 *
 */
@EnableRabbit //开启基于注解的RabbitMQ模式
@SpringBootApplication
public class Springboot10AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot10AmqpApplication.class, args);
    }

}
