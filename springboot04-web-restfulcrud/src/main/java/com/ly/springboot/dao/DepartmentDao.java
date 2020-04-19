package com.ly.springboot.dao;

import com.ly.springboot.entity.Department;
import com.ly.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @Description:
 * @Project: hades
 * @CreateDate: Created in 2020/2/18 12:18
 * @Author: liuyang
 */
@Repository
public class DepartmentDao {

    @Autowired
    EmployeeDao employeeDao;

    private static Map<Integer,Department> departments = null;

    static {
        departments = new HashMap<>();

        departments.put(1,new Department(1,"财务部"));
        departments.put(2,new Department(2,"人事部"));
        departments.put(3,new Department(3,"研发部"));
        departments.put(4,new Department(4,"销售部"));
        departments.put(5,new Department(5,"售后部"));
    }

    public Department getDepartment(Integer id){
        return departments.get(id);
    }

    public Collection<Department> getDepartments(){
        return departments.values();
    }
}
