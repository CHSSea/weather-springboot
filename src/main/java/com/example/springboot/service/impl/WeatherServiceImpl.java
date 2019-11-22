package com.example.springboot.service.impl;

import com.example.springboot.bean.Weather;
import com.example.springboot.mapper.WeatherMapper;
import com.example.springboot.service.WeatherService;
import com.example.springboot.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private WeatherMapper weatherMapper;

    @Override
    public void getWeather(HttpServletResponse response) {
        List<Weather> list = weatherMapper.selectAll();

        long start = System.currentTimeMillis();
        long end;
        try {
            logger.info("Start download Excel");
            ExcelUtil.exportExcelWithObject(response,list,"weather","weather_info.xls",15);
            end = System.currentTimeMillis();
            logger.info("End download Excel" + " " + (end-start) + "ms");
        }catch (IOException e){
            end = System.currentTimeMillis();
            logger.error("Error download excel" + " " +(end - start) + "ms");
        }
    }
}
