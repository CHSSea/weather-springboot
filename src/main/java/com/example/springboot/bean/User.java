package com.example.springboot.bean;

import java.io.Serializable;

/**
 * @program: springboot
 * @description: 用户类
 * @author: Haisheng
 * @create: 2019-04-02 17:14
 **/
public class User implements Serializable {
    private int id;
    private String userName;
    private String password;
    private String redisKey;

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
