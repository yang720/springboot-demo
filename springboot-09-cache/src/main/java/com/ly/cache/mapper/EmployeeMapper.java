package com.ly.cache.mapper;

import com.ly.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @author liuyang
 * @date 2020/3/7 13:35
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 按id获取员工
     * @param id 员工id
     * @return Employee
     */
    @Select("SELECT id,lastName,email,gender,d_id FROM employee WHERE id=#{id}")
    Employee getEmpById(Integer id);

    /**
     * 修改员工信息
     * @param employee 员工实体
     */
    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    void updateEmp(Employee employee);

    /**
     * 按id删除用户
     * @param id 员工id
     */
    @Delete("DELETE FROM employee WHERE id=#{id}")
    void deleteEmpById(Integer id);

    /**
     * 添加员工
     * @param employee 员工实体
     */
    @Insert("INSERT INTO employee(lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    void insertEmployee(Employee employee);

    /**
     * 按名字查询员工
     * @param lastName
     * @return
     */
    @Select("SELECT id,lastName,email,gender,d_id FROM employee WHERE lastName=#{lastName}")
    Employee getEmpByLastName(String lastName);
}
