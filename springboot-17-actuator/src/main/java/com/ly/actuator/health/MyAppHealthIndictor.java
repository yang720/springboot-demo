package com.ly.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

/**
 * @author liuyang
 * @date 2020/3/14 23:56
 */
public class MyAppHealthIndictor implements HealthIndicator {

    @Override
    public Health health() {
        //自定义的检查方法
        //Health.up().build(); 代表健康
        return Health.down().withDetail("msg","服务异常").build();
    }
}
