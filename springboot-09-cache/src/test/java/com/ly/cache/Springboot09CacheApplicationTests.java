package com.ly.cache;

import com.ly.cache.bean.Employee;
import com.ly.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.convert.StringToRedisClientInfoConverter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootTest
class Springboot09CacheApplicationTests {

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate; //操作k-v都是字符串的

    @Resource
    RedisTemplate redisTemplate; //操作k-v都是对象的

    @Resource
    RedisTemplate<Object,Employee> empRedisTemplate;

    /**
     * Redis常见的五大数据类型
     *  String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     *    stringRedisTemplate.opsForValue() [String(字符串)]
     *    stringRedisTemplate.opsForList() [List(列表)]
     *    ...其它类推
     *
     *
     */
    @Test
    public void test01(){
        // 给redis中保存数据
//        stringRedisTemplate.opsForValue().append("msg","hello");
        // 从redis中获取数据
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(msg);

//        stringRedisTemplate.opsForList().leftPush("mylisy","1");
//        stringRedisTemplate.opsForList().leftPush("mylisy","2");
    }

    /**
     * 测试将对象序列化缓存到redis
     */
    @Test
    public void test02(){
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制（需要Employee实现Serializable），序列化后的数据保存到redis中
        //但以jdk序列化存储到redis不便于直接查看
//        redisTemplate.opsForValue().set("emp-01",empById);

        //1、将数据以json的方式保存
          //(1)自己将对像转为json
          //(2)redisTemplate默认的序列化规则
          //(3)自定义empRedisTemplate
        empRedisTemplate.opsForValue().set("emp-02",empById);
    }

    @Test
    void contextLoads() {
        Employee empById = employeeMapper.getEmpById(1);
        System.out.println(empById);
    }

}
