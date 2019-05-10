package com.example.springboot.quartz.listener;

import com.example.springboot.quartz.service.QuartzService;
import com.example.springboot.quartz.task.WeatherJob;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @program: springboot
 * @description: 监听定时任务
 * @author: Haisheng
 * @create: 2019-05-05 14:57
 **/
@Configuration
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartQuartzJobListener.class);
    @Autowired
    private QuartzService quartzService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            //0 0 8 * * ? 代表每天的早上8点执行
            //0 0/2 * * * ? 代表每2分钟执行
            quartzService.createSchedulerJob("Weather","0 0 8 * * ?", WeatherJob.class);
        } catch (SchedulerException e) {
            logger.error("Execute Job error",e);
        }
    }
}
