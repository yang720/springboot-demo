package com.ly.springboot.listener;

import com.ly.springboot.filter.MyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description:
 * @Project: hades
 * @CreateDate: Created in 2020/2/27 00:15
 * @Author: liuyang
 */
public class MyListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("contextInitialized...web应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("contextDestroyed...当前web应用销毁");
    }
}
