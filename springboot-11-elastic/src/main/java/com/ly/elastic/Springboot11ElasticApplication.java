package com.ly.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术来和ElasticSearch交互；
 * 1、Jest（默认不生效）需要导入jest工具包(io.searchbox.client.JestClient)
 * 2、SpringData ElasticSearch【可能与docker拉取的镜像版本不一致】
 *    版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch
 *    1)、Client节点信息clusterNodes，clusterName
 *    2)、ElasticSearchTemplate 操作es
 *    3)、编写一个ElasticSearchRepository的子接口来操作es；
 *
 * 两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 *    1)、编写一个ElasticSearchRepository
 *
 */
@SpringBootApplication
public class Springboot11ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot11ElasticApplication.class, args);
    }

}
