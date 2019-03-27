package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringTestController {
    @RequestMapping(value = "/hello")
    public String welcome(){
        return "Welcome SpringBoot Test";
    }
}
