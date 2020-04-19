package com.ly.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 加上 @MapperScan(value = "com.ly.springboot.mapper")
 * 指定扫描路径下所有的mapper接口，就不需要每个Mapper单独加上@Mapper注解
 * */
@MapperScan(value = "com.ly.springboot.mapper")
@SpringBootApplication
public class Springboot06DataMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot06DataMybatisApplication.class, args);
    }

}
