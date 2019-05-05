package com.example.springboot.quartz.task;

import com.example.springboot.bean.Weather;
import com.example.springboot.jsoup.JsoupWeatherService;
import com.example.springboot.mapper.WeatherMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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

    @Autowired
    private JsoupWeatherService jsoupWeatherService;
    @Autowired
    private WeatherMapper weatherMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Weather> list = null;
        try {
            list = jsoupWeatherService.getWeather();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list != null && list.size() > 0){
            for (Weather weather : list) {
                weatherMapper.save(weather);
            }
        }
    }
}
