package com.ly.springboot.controller;

import com.ly.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Project: hades
 * @CreateDate: Created in 2020/2/26 16:42
 * @Author: liuyang
 */
@ControllerAdvice
public class MyExceptionHandler {

    // 1.浏览器客户端返回的都是json
    /*@ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notExist");
        map.put("massage",e.getMessage());
        map.put("cause",e.getCause());
        return map;
    }*/

    //2.自适应响应效果处理
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        /**
         * Integer statusCode = (Integer) request
         * .getAttribute("javax.servlet.error.status_code");
         * */
        // 传入自己的状态码
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notExist");
        map.put("massage",e.getMessage());
        // 使用了MyErrorAttributes中自定义的错误信息字段
        request.setAttribute("ext",map);
        return "forward:/error";
    }

}
