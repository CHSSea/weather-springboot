package com.example.springboot.jsoup;

import com.example.springboot.bean.Weather;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: springboot
 * @description: 爬取中国天气网的天气数据
 * @author: Haisheng
 * @create: 2019-04-29 14:19
 **/
@Service
public class JsoupWeatherService {
    /**
     * 爬取中国天气网的上海的天气
     * @return List
     * @throws IOException
     */
    public List<Weather> getWeather() throws IOException {
        //通过URL获取HTML内容
        Document doc =  Jsoup.connect("http://www.weather.com.cn/weather/101020100.shtml").timeout(10000).get();
        String title = doc.getElementsByTag("title").text();
        String city = title.substring(0,3).replace("【","");
        //获取div的class="c7d"的值
        Element element = doc.select("div.c7d").first();
        //获取第一个<script>标签
        Element script = element.select("script").first();
        //获取<script>中的内容
        String[] str = script.data().split("=");
        String weather = str[1];
        JSONObject jsonObject = JSONObject.fromObject(weather);
        JSONArray jsonArray = (JSONArray) jsonObject.get("1d");
        List<Weather> list = new ArrayList<Weather>();
        for(int i = 0; i < jsonArray.size();i++){
            String[] strArray = ((String) jsonArray.get(i)).split(",");
            Weather weather1 = new Weather();
            weather1.setTime(strArray[0]);
            weather1.setDn(strArray[1]);
            weather1.setDetail(strArray[2]);
            weather1.setTemperature(strArray[3]);
            weather1.setWind(strArray[4]);
            weather1.setWindSize(strArray[5]);
            weather1.setUnkonow(strArray[6]);
            weather1.setCity(city);
            weather1.setCreateTime(new Date());
            list.add(weather1);
        }
        return list;
    }
}
