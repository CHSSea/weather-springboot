package com.example.springboot.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: springboot
 * @description: 天气的对象
 * @author: Haisheng
 * @create: 2019-04-29 15:23
 **/
@Table(name = "weather")
public class Weather {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "city")
    private String city;
    @Column(name = "time")
    private String time;
    @Column(name = "dn")
    private String dn;
    @Column(name = "detail")
    private String detail;
    @Column(name = "temperature")
    private String temperature;
    @Column(name = "wind")
    private String wind;
    @Column(name = "windSize")
    private String windSize;
    @Column(name = "unkonow")
    private String unkonow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWindSize() {
        return windSize;
    }

    public void setWindSize(String windSize) {
        this.windSize = windSize;
    }

    public String getUnkonow() {
        return unkonow;
    }

    public void setUnkonow(String unkonow) {
        this.unkonow = unkonow;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", dn='" + dn + '\'' +
                ", detail='" + detail + '\'' +
                ", temperature='" + temperature + '\'' +
                ", wind='" + wind + '\'' +
                ", windSize='" + windSize + '\'' +
                ", unkonow='" + unkonow + '\'' +
                '}';
    }
}
