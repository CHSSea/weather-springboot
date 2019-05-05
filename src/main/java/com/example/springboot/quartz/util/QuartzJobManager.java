package com.example.springboot.quartz.util;

import com.example.springboot.bean.AppQuartz;
import com.example.springboot.quartz.task.JobOne;
import com.example.springboot.quartz.task.JobTwo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springboot
 * @description: Job的工具类
 * @author: Haisheng
 * @create: 2019-04-30 11:13
 **/
@Component
public class QuartzJobManager {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建Job
     * @param appQuartz
     * @return
     */
    public String addJob(AppQuartz appQuartz) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=df.parse(appQuartz.getStartTime());

        /*if (!CronExpression.isValidExpression(appQuartz.getCronExpression())) {
            return "Illegal cron expression";
        }*/

        JobDetail jobDetail=null;

        if("JobOne".equals(appQuartz.getJobGroup())) {
            jobDetail = JobBuilder.newJob(JobOne.class).withIdentity(appQuartz.getJobName(), appQuartz.getJobGroup()).build();
        }
        if("JobTwo".equals(appQuartz.getJobGroup())) {
            jobDetail = JobBuilder.newJob(JobTwo.class).withIdentity(appQuartz.getJobName(), appQuartz.getJobGroup()).build();
        }

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(appQuartz.getCronExpression()).withMisfireHandlingInstructionDoNothing();

        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(appQuartz.getJobName(), appQuartz.getJobGroup()).startAt(date)
                .withSchedule(scheduleBuilder).build();

        if(appQuartz.getInvokeParam()!=null && !"".equals(appQuartz.getInvokeParam())) {
            trigger.getJobDataMap().put("invokeParam",appQuartz.getInvokeParam());
        }

        scheduler.scheduleJob(jobDetail, trigger);
        return "success";
    }

    public String getJobState(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(jobName, jobGroup);
        return scheduler.getTriggerState(triggerKey).name();
    }

    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    public String pauseJob(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "fail";
        }else {
            scheduler.pauseJob(jobKey);
            return "success";
        }

    }

    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    public String resumeJob(String jobName, String jobGroup) throws SchedulerException {

        JobKey jobKey = new JobKey(jobName, jobGroup);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "fail";
        }else {
            scheduler.resumeJob(jobKey);
            return "success";
        }
    }

    public String  deleteJob(AppQuartz appQuartz) throws SchedulerException {
        JobKey jobKey = new JobKey(appQuartz.getJobName(), appQuartz.getJobGroup());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null ) {
            return "jobDetail is null";
        }else if(!scheduler.checkExists(jobKey)) {
            return "jobKey is not exists";
        }else {
            scheduler.deleteJob(jobKey);
            return "success";
        }

    }

    public String  modifyJob(AppQuartz appQuartz) throws SchedulerException {
        if (!CronExpression.isValidExpression(appQuartz.getCronExpression())) {
            return "Illegal cron expression";
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(appQuartz.getJobName(),appQuartz.getJobGroup());
        JobKey jobKey = new JobKey(appQuartz.getJobName(),appQuartz.getJobGroup());
        if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //表达式调度构建器,不立即执行
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(appQuartz.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
            //修改参数
            if(!trigger.getJobDataMap().get("invokeParam").equals(appQuartz.getInvokeParam())) {
                trigger.getJobDataMap().put("invokeParam",appQuartz.getInvokeParam());
            }
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            return "success";
        }else {
            return "job or trigger not exists";
        }

    }

}

