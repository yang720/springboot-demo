package com.ly.cache.service;

import com.ly.cache.bean.Employee;
import com.ly.cache.mapper.EmployeeMapper;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuyang
 * @date 2020/3/7 13:53
 */
@Service
@CacheConfig(cacheNames = "emp") //抽取缓存的公共配置，其它缓存注解不需要写 value = "emp"
public class EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后查询相同数据，直接从缓存中获取，不用调用方法
     *
     * CacheManager管理多个Cache组件，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一的名字
     *
     * 原理：
     *   1、自动配置类：CacheAutoConfiguration
     *   2、缓存的配置类：GenericCacheConfiguration...等等
     *   3、哪个配置类默认生效：SimpleCacheConfiguration
     *   4、给容器中注册了一个CacheManager；ConcurrentMapCacheManager
     *   5、可以获取和创建ConcurrentMapCache类型的缓存组件，它的作用将数据保存在ConcurrentMap中
     *
     * 运行流程：
     *   @Cacheable：
     *   1、方法运行之前，先去查询Cache(缓存组件)，按照cacheNames指定的名字获取，
     *      (CacheManager先获取相应的缓存)，第一次获取缓存如果没有Cache组件会自动创建；
     *   2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数，
     *      key是按照某种策略生成的，默认使用KeyGenerator生成的，而KeyGenerator默认使用SimpleKeyGenerator实现并生成key；
     *             SimpleKeyGenerator生成key的默认策略：
     *                  如果没有参数：key = new SimpleKey();
     *                  如果有一个参数：key = 参数的值;
     *                  如果有多个参数：key = new SimpleKey(params);
     *   3、没有查到缓存就调用目标方法；
     *   4、将目标方法返回的结果放进缓存中；
     *
     *   @Cacheable标注的方法执行前先来检查缓存中是否存在该数据，默认按照参数的值作为key去查询缓存，若没有则执行查询方法并将结果放入缓存，之后再来调用直接使用缓存中的数据；
     *
     *   核心：
     *      1) 使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *      2) key使用keyGeneration生成的，默认是SimpleKeyGeneration
     *
     * =================
     *   几个属性：
     *       cacheNames/value：指定缓存的名字
     *       key：缓存数据使用的key，可以用它来指定。默认是使用方法参数的值
     *             编写SpEL;  #id：参数id的值  等同于 #root.args[0]
     *             例：自定义key，传入下标；  getEmp[2] ==>  key = "#root.methodName+'['+ #id +']'"
     *
     *       keyGenerator：key的生成器，可以自己制定key的生成器的组件id
     *             key/keyGenerator：二选一使用
     *             keyGenerator = "myKeyGenerator"  myKeyGenerator为MyCacheConfig配置文件中自定义的key
     *
     *       cacheManager：指定缓存管理器，或者cacheResolver指定获取解析器
     *       condition：指定符合条件的情况下才缓存，
     *             condition = "#id>10"   缓存id大于10的数据
     *
     *       unless：否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存，可以获取到结果进行判断
     *             unless = "#result == null"   结果为null的不进行缓存
     *       sync：是否使用异步模式
     *
     * @param id 形参：员工id
     * @return Employee 员工实体
     */
    @Cacheable(cacheNames = {"emp"} )
    public Employee getEmpById(Integer id){
        System.out.println("查询编号为"+id+"的员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut:即调用方法，又更新缓存数据(修改数据库的某条数据，同时更新缓存)，同步更新缓存
     *
     * 运行时机：
     *    1、先调用目标方法
     *    2、将目标方法的结果缓存起来
     *
     * 测试步骤：
     *    1、查询1号员工，查到的数据放在缓存中；
     *           key:1  value:{lastName=张三,gender=1}
     *    2、以后查询还是之前的结果
     *    3、更新1号员工；  lastName:zhangsan,gender:0
     *           将方法的返回值也放进缓存了，
     *           key:传入的employee对象   value:返回的employee对象
     *    4、再次查询1号员工？
     *         应该是更新后的员工；
     *           key = "#employee.id"  使用传入参数的员工id
     *           key = "#result.id"    使用返回后的id
     *              @Cacheable的key不能使用#result
     *         为什么还是没更新前的数据？【1号员工没有在缓存中更新】
     *
     *
     * @param employee 员工实体
     * @return employee
     */
    @CachePut(value = "emp",key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict：缓存清除
     *   key = "#id"        清除指定的key，下次在查询该数据需要重新读取数据库
     *   allEntries = true  清除CacheName={"emp"}下的所有缓存数据
     *   beforeInvocation = false  是否在方法执行之前清除缓存，
     *       默认缓存清除是在方法运行之后执行【如果执行的方法异常，则不会清除缓存】
     *
     *   beforeInvocation = true  在方法执行之前缓存清除【无论方法是否出现异常，缓存都会清除】
     */
    @CacheEvict(value = "emp",key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp:"+id);
        //employeeMapper.deleteEmpById(id);
    }

    /**
     * @Caching：定义复杂的缓存规则
     *   按lastName字段查询出数据后，分别以id和email作为key放在缓存中
     *   下次按id或者email查询该条数据时，直接从缓存读取，不需要从数据库获取
     *
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        Employee emp = employeeMapper.getEmpByLastName(lastName);
        return emp;
    }
}
