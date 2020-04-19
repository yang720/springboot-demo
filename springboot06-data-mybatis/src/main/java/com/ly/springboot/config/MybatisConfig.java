package com.ly.springboot.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;

/**
 * @author liuyang
 * @date 2020/3/5 19:32
 */
@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                /**
                 * 配置MyBatis开启驼峰命名法
                 *
                 * 若不开启，且数据库字段以department_name方式命名
                 * 在DepartmentMapper中sql语句为：insert into department(department_name) values(#{departmentName})
                 * 那么 department_name 字段是无法匹配到的
                 * */
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
