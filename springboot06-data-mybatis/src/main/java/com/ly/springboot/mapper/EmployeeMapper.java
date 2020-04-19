package com.ly.springboot.mapper;

import com.ly.springboot.bean.Employee;

/**
 * @author liuyang
 * @date 2020/3/5 19:55
 */
//@Mapper 主程序入口已经使用@MapperScan进行了扫描
public interface EmployeeMapper {

    /**
     *
     * */
    public Employee getEmpById(Integer id);

    /**
     *
     * */
    public void insertEmp(Employee employee);
}
