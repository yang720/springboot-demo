package com.ly.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author liuyang
 * @date 2020/3/5 15:07
 */
@Controller
public class HelloController {

    @Resource
    JdbcTemplate jdbcTemplate;

    @GetMapping("/query")
    @ResponseBody
    public Map<String,Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select id,departmentName FROM department");
        return list.get(0);
    }
}
