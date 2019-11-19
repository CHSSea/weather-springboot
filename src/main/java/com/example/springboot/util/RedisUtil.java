package com.example.springboot.util;

import com.example.springboot.controller.RedisTestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: springboot
 * @description: Redis工具类
 * @author: Haisheng
 * @create: 2019-04-03 15:46
 **/
@Component
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 向Redis添加key和value
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过key查询value
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean setWithTime(String key,String value,long time){
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key,value,time, TimeUnit.MINUTES);
            result = true;
        }catch (Exception e){
            result = false;
            logger.error("Can't connection to redis");
        }
        return result;
    }

    public boolean remove(String key){
        boolean result = false;
        try{
            return  redisTemplate.delete(key);
        }catch (Exception e){
            return result;
        }
    }
}
