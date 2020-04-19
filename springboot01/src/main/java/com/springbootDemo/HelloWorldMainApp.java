package com.springbootDemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuyang
 * @SpringBootApplication 来标注一个主程序类，说明这是Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApp {

    public static void main(String[] args) {
        //启动springboot应用
        SpringApplication.run(HelloWorldMainApp.class, args);
    }
}
