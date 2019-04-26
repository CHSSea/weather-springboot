package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: springboot
 * @description: SpringBoot启动类
 * @author: Haisheng
 * @create: 2019-04-10 15:51
 **/
@EnableCaching
@SpringBootApplication
@MapperScan("com.example.springboot.mapper")
public class SpringbootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(SpringbootApplication.class);
    }
}
