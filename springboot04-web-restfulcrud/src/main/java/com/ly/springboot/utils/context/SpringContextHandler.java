package com.ly.springboot.utils.context;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.validation.constraints.NotNull;

/**
 * @author liuyang
 * @date 2020/2/29 18:11
 */
@Slf4j
public class SpringContextHandler implements ApplicationContextAware {

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext)
            throws BeansException {
        SpringUtils.initContext(applicationContext);
        log.info("Spring Context自动初始化完成，可以通过SpringUtils类提供的方法来获取Bean.");
    }
}