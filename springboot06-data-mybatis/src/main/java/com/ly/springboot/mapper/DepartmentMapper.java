package com.ly.springboot.mapper;

import com.ly.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * 部门信息Mapper
 * @author liuyang
 * @date 2020/3/5 18:03
 */
//@Mapper  主程序入口已经使用@MapperScan进行扫描
public interface DepartmentMapper {

    /**
     * 按id查询部门信息
     * @param id 部门id
     * @return Department
     * */
    @Select("select id,departmentName from department where id=#{id}")
    public Department getDeptById(Integer id);

    /**
     * 按id删除部门信息
     * @param id 部门id
     * @return int
     * */
    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    /**
     * 添加部门
     * useGeneratedKeys 是否使用自动生成的主键
     * keyProperty 设置哪个属性为主键
     * @param department 部门
     * @return int
     * */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    /**
     * 修改部门信息
     * @param department 修改后的部门信息
     * @return int
     * */
    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
