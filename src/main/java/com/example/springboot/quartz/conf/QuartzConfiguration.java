package com.example.springboot.quartz.conf;

import com.example.springboot.quartz.TaskJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

/**
 * @program: springboot
 * @description: Quartz定时任务配置类
 * @author: Haisheng
 * @create: 2019-04-30 10:34
 **/
@Configuration
public class QuartzConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TaskJobFactory jobFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        System.out.println("Start Job" + event.getSource());
    }

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(propertiesFactoryBean.getObject());
        factory.setJobFactory(jobFactory);
        return  factory;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }
}
