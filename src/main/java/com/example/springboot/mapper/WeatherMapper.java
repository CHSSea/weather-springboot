package com.example.springboot.mapper;

import com.example.springboot.bean.Weather;
import com.example.springboot.mapperbase.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
 * @program: springboot
 * @description: 天气预报信息的mapper类
 * @author: Haisheng
 * @create: 2019-05-05 15:32
 **/
public interface WeatherMapper extends BaseMapper<Weather> {

    @Insert("insert into weather (city,time,dn,detail,temperature,wind,windSize,unknow) values " +
            "(#{city},#{time},#{dn},#{detail},#{temperature},#{wind},#{windSize},#{unknow})")
    void save(Weather weather);
}
