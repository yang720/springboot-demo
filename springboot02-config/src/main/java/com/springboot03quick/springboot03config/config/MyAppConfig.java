package com.springboot03quick.springboot03config.config;

import com.springboot03quick.springboot03config.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ASUS
 * @Configuration: 指明当前类是配置类，就是来代替之前的Spring配置文件
 * <p>
 * 在配置文件中用<bean></bean>标签添加组件
 */

@Configuration
public class MyAppConfig {
    //将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
    @Bean
    public HelloService helloService() {
        System.out.println("配置类@Bean给容器中添加组件了...");
        return new HelloService();
    }
}
