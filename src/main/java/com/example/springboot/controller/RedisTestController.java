package com.example.springboot.controller;

import com.example.springboot.sms.SendSms;
import com.example.springboot.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(RedisTestController.class);

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

    /**
     * 获取验证码并且发送短信
     * @param phoneNum
     * @return
     */
    @PostMapping(value = "/verify")
    public Object getCodeAndSendSMS(String phoneNum){
        boolean result = false;
        String code = String.valueOf((int)((Math.random()*9+1)*1000));
        result = redisUtil.setWithTime(phoneNum,code,30);
        logger.info("redis:"+result);
        String json = " {" + "code" + " : "+code+"} ";
        //发送短信
        SendSms.sendSMS(phoneNum,"LTAIwZYWq65HFXS9","tPvoHA2p0KRnffkgSmBJhANSyJedv7",
                "Weather","SMS_164665704",json);
        return code;
    }

    @PostMapping(value = "/login")
    public Object login(String phoneNum,String code){
        Object value = redisUtil.get(phoneNum);
        if (value == null){
            return "code is invalid";
        }else {
            if (value.equals(code)){
                //删除redis的数据
                redisUtil.remove(phoneNum);
                return "SUCCESS";
            }else {
                return "code is invalid";
            }
        }
    }

}
