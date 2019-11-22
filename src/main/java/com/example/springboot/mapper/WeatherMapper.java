package com.example.springboot.mapper;

import com.example.springboot.bean.Weather;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: springboot
 * @description: 天气预报信息的mapper类
 * @author: Haisheng
 * @create: 2019-05-05 15:32
 **/
@Mapper
public interface WeatherMapper extends tk.mybatis.mapper.common.Mapper<Weather>, tk.mybatis.mapper.common.MySqlMapper<Weather> {

    @Insert("insert into weather (city,time,dn,detail,temperature,wind,windSize,unknow,createTime) values " +
            "(#{city},#{time},#{dn},#{detail},#{temperature},#{wind},#{windSize},#{unknow},#{createTime})")
    void save(Weather weather);

    @Select("select id,city,time,dn,detail,temperature,wind,windSize,unknow,createTime from weather")
    List<Weather> selectWeather();
}
