package com.ly.springboot.entity;

import javax.persistence.*;

/**
 * 使用JPA注解配置映射关系
 *
 * @author liuyang
 * @date 2020/3/6 17:36
 */

@Entity //告诉JPA这是一个实体类（和表数据映射的类）
@Table(name = "tbl_user") //@Table来指定和哪个表数据对应，如果省略默认表名就是user
public class User {

    /**
     * id
     * | @Id 这是一个主键
     * | @GeneratedValue(strategy = GenerationType.IDENTITY) 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * lastName
     * | @Column(name = "last_name",length = 50)  这是和数据表对应的一个列
     *
     */
    @Column(name = "last_name",length = 50)
    private String lastName;
    /**
     * email
     * | @Column 省略默认列名就是属性名
     */
    @Column
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
