package com.example.springboot.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: springboot
 * @description: 用户类
 * @author: Haisheng
 * @create: 2019-04-02 17:14
 **/
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "redisKey")
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", redisKey='" + redisKey + '\'' +
                '}';
    }
}
