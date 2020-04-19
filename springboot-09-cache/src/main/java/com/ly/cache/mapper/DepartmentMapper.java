package com.ly.cache.mapper;

import com.ly.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuyang
 * @date 2020/3/7 13:35
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 根据id获取部门
     * @param id 部门标识列
     * @return
     */
    @Select("SELECT id,departmentName FROM department WHERE id = #{id}")
    Department getDeptById(Integer id);
}
