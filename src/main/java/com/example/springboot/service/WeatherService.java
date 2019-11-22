package com.example.springboot.service;

import javax.servlet.http.HttpServletResponse;

public interface WeatherService {

    void getWeather(HttpServletResponse response);
}
