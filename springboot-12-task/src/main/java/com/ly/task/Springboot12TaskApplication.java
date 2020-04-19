package com.ly.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 1、异步任务
 *   1)、@EnableAsync：开启异步注解功能
 *   2)、@Async：告诉spring需要异步执行的方法
 * 2、定时任务
 *   1)、@EnableScheduling：开启基于注解的定时任务
 *   2)、
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class Springboot12TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot12TaskApplication.class, args);
    }

}
