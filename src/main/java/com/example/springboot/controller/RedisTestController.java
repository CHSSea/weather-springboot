package com.example.springboot.controller;

import com.example.springboot.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springboot
 * @description: test Redis class
 * @author: haisheng
 * @create: 2019-04-02 16:55
 **/

@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    public RedisUtil redisUtil;

    @RequestMapping(value = "/set")
    public String set(){
        String username = "mumu";
        String password = "111";
        String a="haisheng";//git测试
        boolean result = redisUtil.set(username,password);
        if(result){
            return "SUCCESS";
        }else{
            return "FALSE";
        }
    }

    @GetMapping("/get/{key}")
    public Object get(@PathVariable(name = "key") String key){
        return redisUtil.get(key);
    }

    @PostMapping(value = "find")
    @ResponseBody
    public Object find(String key){
        return redisUtil.get(key);
    }

}
