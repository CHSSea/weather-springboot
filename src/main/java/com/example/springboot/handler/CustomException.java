package com.example.springboot.handler;

/**
 * @program: springboot
 * @description: 全局异常实体类
 * @author: Haisheng
 * @create: 2019-05-07 17:26
 **/
public class CustomException extends RuntimeException{
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomException(){}

    public CustomException(String code, String message){
        this.code = code;
        this.message = message;
    }
}
