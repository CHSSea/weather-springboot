package com.example.springboot.mapper;

import com.example.springboot.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: springboot
 * @description: User的mapper类
 * @author: Haisheng
 * @create: 2019-04-26 10:42
 **/
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>, tk.mybatis.mapper.common.MySqlMapper<User> {

    @Select("select * from user where id = #{id}")
    User findById(int id);

    @Select("select * from user where id = #{id}")
    User find(@Param("id") int id);
}
