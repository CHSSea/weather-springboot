package com.example.springboot.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
    @Column(name = "unknow")
    private String unknow;
    @Column(name = "createTime")
    private Date createTime;

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", time='" + time + '\'' +
                ", dn='" + dn + '\'' +
                ", detail='" + detail + '\'' +
                ", temperature='" + temperature + '\'' +
                ", wind='" + wind + '\'' +
                ", windSize='" + windSize + '\'' +
                ", unknow='" + unknow + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public void setUnknow(String unknow) {
        this.unknow = unknow;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public String getUnknow() {
        return unknow;
    }

    public void setUnkonow(String unknow) {
        this.unknow = unknow;
    }

}
