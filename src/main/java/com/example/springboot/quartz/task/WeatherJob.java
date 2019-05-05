package com.example.springboot.quartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @program: springboot
 * @description: 爬取天气的定时任务
 * @author: Haisheng
 * @create: 2019-05-05 14:03
 **/
public class WeatherJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hello World!");
    }
}
