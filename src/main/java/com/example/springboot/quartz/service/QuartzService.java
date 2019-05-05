package com.example.springboot.quartz.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**0/
 * @program: springboot
 * @description: 定时任务服务类
 * @author: Haisheng
 * @create: 2019-05-05 14:49
 **/
@Service
public class QuartzService {
    private static final String JOB_GROUP = "weather_Group";
    private static final String TRIGGER_GROUP = "weather_trigger_group";

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建定时任务
     * @param jobDetailName
     * @param cronExpression
     * @param jobClass
     * @throws SchedulerException
     */
    public void createSchedulerJob(String jobDetailName, String cronExpression, Class<? extends Job> jobClass) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity("task_" + jobDetailName,JOB_GROUP).storeDurably()
                .requestRecovery().build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("task_" + jobDetailName, TRIGGER_GROUP).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
