package com.example.springboot.controller;

import com.example.springboot.bean.AppQuartz;
import com.example.springboot.quartz.util.QuartzJobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description: 定时任务的测试类
 * @author: Haisheng
 * @create: 2019-04-30 13:59
 **/
@RestController
public class JobController {

    @Autowired
    private QuartzJobManager quartzJobManager;

    @RequestMapping(value = "/addJob",method = RequestMethod.POST)
    public void addJob(@RequestBody AppQuartz appQuartz) throws Exception {
        String str = quartzJobManager.addJob(appQuartz);
        System.out.println(str);
    }
}
