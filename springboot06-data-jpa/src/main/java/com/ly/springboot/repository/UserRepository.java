package com.ly.springboot.repository;

import com.ly.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 继承JpaRepository来完成对数据库的操作
 *
 * @author liuyang
 * @date 2020/3/6 17:54
 */
public interface UserRepository extends JpaRepository<User,Integer> {

}
