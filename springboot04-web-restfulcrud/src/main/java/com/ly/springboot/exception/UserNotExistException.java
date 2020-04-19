package com.ly.springboot.exception;

/**
 * @Description:
 * @Project: hades
 * @CreateDate: Created in 2020/2/26 16:05
 * @Author: liuyang
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在");
    }
}

