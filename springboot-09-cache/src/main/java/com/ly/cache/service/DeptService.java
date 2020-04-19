package com.ly.cache.service;

import com.ly.cache.bean.Department;
import com.ly.cache.mapper.DepartmentMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuyang
 * @date 2020/3/9 14:38
 */
@Service
public class DeptService {

    @Resource
    DepartmentMapper departmentMapper;

    @Resource
    RedisCacheManager redisCacheManager;

    /**
     *
     * @param id
     * @return dept 部门实体
     */
    @Cacheable(value = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查询部门："+id);
        return departmentMapper.getDeptById(id);
    }

    /**
     * 使用缓存管理器获取及操作cache
     * @param id
     * @return
     */
//    public Department getDeptById(Integer id){
//        System.out.println("查询部门："+id);
//        Department dept = departmentMapper.getDeptById(id);
//
//        //获取某个缓存
//        Cache deptCache = redisCacheManager.getCache("dept");
//        deptCache.put("dept"+id,dept);
//        return dept;
//    }
}
