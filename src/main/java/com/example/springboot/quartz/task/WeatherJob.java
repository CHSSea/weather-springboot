package com.example.springboot.quartz.task;

import com.example.springboot.bean.Weather;
import com.example.springboot.handler.CustomException;
import com.example.springboot.jsoup.JsoupWeatherService;
import com.example.springboot.mapper.WeatherMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * @program: springboot
 * @description: 爬取天气的定时任务
 * @author: Haisheng
 * @create: 2019-05-05 14:03
 **/
public class WeatherJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(Weather.class);
    @Autowired
    private JsoupWeatherService jsoupWeatherService;
    @Autowired
    private WeatherMapper weatherMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Start Job");
        Long startTime = null;
        Long endTime = null;
        List<Weather> list = null;
        try {
            startTime = System.currentTimeMillis();
            list = jsoupWeatherService.getWeather();
            endTime = System.currentTimeMillis();
        } catch (IOException e) {
            endTime = System.currentTimeMillis();
            logger.info("Job Error");
            throw new CustomException("100",e.getMessage());
        }finally {
            logger.info("Get weather info from WeatherWeb spend : "+(endTime-startTime)+"ms");
        }
        if(list != null && list.size() > 0){
            for (Weather weather : list) {
                weatherMapper.save(weather);
            }
        }
        logger.info("End Job");
    }
}
