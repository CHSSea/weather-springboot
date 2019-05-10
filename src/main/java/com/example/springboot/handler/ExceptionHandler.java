package com.example.springboot.handler;

import net.sf.json.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description: 全局的异常处理类
 * @author: Haisheng
 * @create: 2019-05-07 16:31
 **/
@RestController
@ControllerAdvice
public class ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e){
        logger.error(ExceptionUtils.getFullStackTrace(e));
        String msg = e.getMessage();
        if(msg == null || msg.equals("")){
            msg = "System error";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message",msg);
        return jsonObject;
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
    public Object customException(CustomException e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",e.getCode());
        jsonObject.put("message",e.getMessage());
        return jsonObject;
    }
}