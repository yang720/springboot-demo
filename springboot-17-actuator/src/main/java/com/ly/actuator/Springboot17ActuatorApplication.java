package com.ly.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 引入spring-boot-starter-actuator
 * 可使用springboot提供的准生产环境下的应用监控和管理功能；
 * 可通过HTTP，JMX，SSH 协议进行操作，自动得到审计、健康及指标信息等
 * 如启动后使用 http://localhost:8080/actuator 查看，更多请查看文档
 *
 * 自定义健康状态指示器
 *    1、编写一个指示器实现 HealthIndicator 接口
 */
@SpringBootApplication
public class Springboot17ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot17ActuatorApplication.class, args);
    }

}
