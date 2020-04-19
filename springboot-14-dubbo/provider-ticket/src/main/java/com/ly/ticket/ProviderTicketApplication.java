package com.ly.ticket;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 这是alibaba的dubbo(几年前已不再更新)，阿里将dubbo捐给Apache，19年成为Apache顶级项目，新版dubbo可使用Apache版本的
 * 1、将服务提供者注册到注册中心
 *     1、引入dubbo和zookeeper相关依赖
 *     2、配置dubbo.scan.base-packages指定扫描包和注册中心地址路径
 *     3、service使用@Service将服务发布出去，通过dubbo.registry.address指定发布的地址
 *
 * 2、注意：
 *     1）、生产和消费者主入口@EnableDubbo开启dubbo注解
 *     2）、生产者接口实现类使用 alibaba.dubbo 下的@Service注解
 *     3）、消费者在自己的service中使用 @Reference 注入生产者的服务
 *     4）、生产者和消费者暴露的接口路径名要一致
 */

@EnableDubbo
@SpringBootApplication
public class ProviderTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }

}
