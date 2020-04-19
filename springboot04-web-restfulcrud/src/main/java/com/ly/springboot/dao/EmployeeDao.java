package com.ly.springboot.dao;

import com.ly.springboot.entity.Department;
import com.ly.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import sun.util.calendar.BaseCalendar;
import sun.util.resources.sk.CalendarData_sk;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Project: hades
 * @CreateDate: Created in 2020/2/18 12:14
 * @Author: liuyang
 */
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001,"E-AA","aa@qq.com",1,new Department(1,"财务部"),new Date()));
        employees.put(1002, new Employee(1002,"E-BB","bb@qq.com",1,new Department(2,"研发部"),new Date()));
        employees.put(1003, new Employee(1003,"E-CC","cc@qq.com",0,new Department(5,"售后部"),new Date()));
        employees.put(1004, new Employee(1004,"E-DD","dd@qq.com",0,new Department(3,"销售部"),new Date()));
        employees.put(1005, new Employee(1005,"E-EE","ee@qq.com",1,new Department(4,"人事部"),new Date()));
    }

    private static Integer initId = 1006;

    public void save(Employee employee){
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    // 查询所有员工
    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee get(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }
}
