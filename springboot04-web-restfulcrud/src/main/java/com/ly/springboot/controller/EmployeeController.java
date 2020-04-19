package com.ly.springboot.controller;

import com.ly.springboot.dao.DepartmentDao;
import com.ly.springboot.dao.EmployeeDao;
import com.ly.springboot.entity.ApiResp;
import com.ly.springboot.entity.Department;
import com.ly.springboot.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 员工处理器
 *
 * @author liuyang
 * @date 2020/2/18 12:03
 */

@Slf4j
@Controller
public class EmployeeController {

    @Resource
    EmployeeDao employeeDao;
    @Resource
    DepartmentDao departmentDao;

    //private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * 查询所有员工返回列表页面
     * */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    /**
     * 来到添加员工页面
     * */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    /**
     * 保存员工信息
     * */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        log.info("保存员工信息："+employee);

        employeeDao.save(employee);
        // redirect:表示重定向到一个地址  /代表当前项目路径
        // forward:转发到一个地址
        return "redirect:/emps";
    }

    /**
     * 修改页面,查出当前员工
     * */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        // 页面显示部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        // 回到修改页面(add是一个修改添加二合一的页面)
        return  "emp/add";
    }
    /**
     * 员工修改
     * */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        log.info("修改员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    /**
     * 表单提交员工删除
     * */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
    /**
     * Ajax删除员工
     * */
    @DeleteMapping("/delEmp/{id}")
    @ResponseBody
    public ApiResp ajaxDelEmp(@PathVariable("id") String id){
        employeeDao.delete(Integer.valueOf(id));
        return ApiResp.retOK("success");
    }


    /**
     * 其它页面
     * */
    @GetMapping("/icon")
    public String iconFontAweSome(){
        return "icon-fontawesome";
    }
    @GetMapping("/map")
    public String mapGoogle(){
        return "map-google";
    }
    @GetMapping("/pagesBlank")
    public String pagesBlank(){
        return "pages-blank";
    }
    @GetMapping("/pagesError")
    public String pagesError404(){
        return "pages-error-404";
    }
    @GetMapping("/pagesProfile")
    public String pagesProfile(){
        return "pages-profile";
    }

}
