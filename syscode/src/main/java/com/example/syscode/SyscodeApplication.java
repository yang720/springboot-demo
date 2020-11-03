package com.example.syscode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.syscode.mapper")
public class SyscodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyscodeApplication.class, args);
    }

}
