package com.example.springboot.controller;

import com.example.springboot.bean.Weather;
import com.example.springboot.service.WeatherService;
import com.example.springboot.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Haisheng
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/download")
    public void excelDownload(HttpServletResponse response){
        List<List<String>> excelData = new ArrayList<>();

        List<String> head = new ArrayList<>();
        head.add("第一列");
        head.add("第二列");
        head.add("第三列");

        List<String> data1 = new ArrayList<>();
        data1.add("123");
        data1.add("234");
        data1.add("345");

        List<String> data2 = new ArrayList<>();
        data2.add("abc");
        data2.add("bcd");
        data2.add("cde");

        excelData.add(head);
        excelData.add(data1);
        excelData.add(data2);

        String sheetName = "测试";
        String fileName = "ExcelTest.xls";

        long start = System.currentTimeMillis();;
        long end;
        try {
            logger.info("Start download Excel");
            ExcelUtil.exportExcel(response,excelData,sheetName,fileName,15);
            end = System.currentTimeMillis();
        } catch (IOException e) {
            end = System.currentTimeMillis();
            logger.error("Error download" + e.getMessage());
        }
        logger.info("End download Excel,spend " + (end - start));
    }

    @GetMapping("/downloadExcel")
    public void excelDownloadWithObject(HttpServletResponse response){
        weatherService.getWeather(response);
    }
}
