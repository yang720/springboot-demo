package com.ly.springboot.entity;

import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Project: hades
 * @CreateDate: Created in 2020/2/18 12:17
 * @Author: liuyang
 */

public class Department {
    private Integer id;
    private String departmentName;

    public Department() {

    }

    public Department(int id ,String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
