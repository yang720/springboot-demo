package com.ly.providerticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 生产者
 * 启动多个服务端口：
 *    1、确认provider-ticket运行成功
 *    2、点击对应Maven->Lifecycle->package打成jar包(打包前修改配置中的端口号来提供不同服务端口，需要多少个服务打多少个包)
 *    3、打成的多个jar包修改成以端口号为后缀,如XXX-8001.jar，方便分辨
 *    4、cmd命令：java -jar XXX.jar 启动所有服务
 * 在消费者入口RestTemplate方法使用@LoadBalanced注解，以提高负载均衡的能力
 */
@SpringBootApplication
public class ProviderTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }

}
