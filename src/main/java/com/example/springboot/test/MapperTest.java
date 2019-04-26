package com.example.springboot.test;

import com.example.springboot.bean.User;
import com.example.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: springboot
 * @description: Mapper的单元测试类
 * @author: Haisheng
 * @create: 2019-04-26 10:46
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 自定义的find方法
     */
    @Test
    public void testUserMapper1(){
        User user = userMapper.find(1);
        System.out.println(user.toString());
    }

    /**
     * mapper4.0自带的方法
     */
    @Test
    public void testUserMapper2(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.toString());
    }
}
