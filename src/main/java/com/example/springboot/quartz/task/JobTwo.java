package com.example.springboot.quartz.task;

import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @program: springboot
 * @description: 定时任务实体类
 * @author: Haisheng
 * @create: 2019-04-30 13:55
 **/
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class JobTwo implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap data=jobExecutionContext.getTrigger().getJobDataMap();
        String invokeParam =(String) data.get("invokeParam");
        System.out.println("Start Job Two");
    }
}
