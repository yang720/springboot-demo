package com.ly.amqp;

import com.ly.amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot10AmqpApplicationTests {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    AmqpAdmin amqpAdmin;

    /**
     * 创建交换器、队列、绑定规则...或者删除交换器[deleteExchange()]和队列[deleteQueue()]
     *   交换器：
     *     xxx.direct  点对点：【如routingKey为ly.news 则只匹配名为ly.news的队列】
     *     xxx.fanout  广播：【无论routingKey是什么都会匹配到】
     *     xxx.topic   匹配：【routingKey为ly.# 匹配ly.开头的所有队列，不论单词个数】
     *                      【routingKey为ly.* 匹配ly.后面只有一个单词的队列】
     *                      反之【#.xxx】【*.xxx】类推
     */
    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建交换器完成");

        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        System.out.println("创建队列完成");

        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","haha.news",null));
        System.out.println("绑定规则完成");
    }

    /**
     * 1、单播（点对点）
     */
    @Test
    void contextLoads() {
        //Message需要自己构造一个；定义消息体内容和消息头
//        rabbitTemplate.send(exchange,routeKey,message);

        //object默认大昂陈消息体，只需要传入发送的对象，自动序列化发送给rabbitmq
//        rabbitTemplate.convertAndSend(exchange,routeKey,object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("msg", Arrays.asList("hello rabbit", 123, true));
        //对象被默认序列化后发送出去
//        rabbitTemplate.convertAndSend("exchange.direct", "ly.news", map);
        rabbitTemplate.convertAndSend("exchange.direct", "ly.news", new Book("西游记","吴承恩"));
    }

    /**
     * 接收数据,如何将数据自动转为json发送出去：自定义MessageMessageConverter
     */
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("ly.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 2、广播
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","", new Book("三国演义","罗贯中"));
    }

    @Test
    public void sendMsg02(){
        rabbitTemplate.convertAndSend("exchange.fanout","", new Book("红楼梦","曹雪芹"));
    }
}
