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
     * 爬取中国天气网的上海和南京的天气
     * @return List
     * @throws IOException
     */
    public List<Weather> getWeather() throws IOException{
        //1.上海 2.南京
        String[] cityList = {"101020100","101190101"};
        String url;
        int length = cityList.length;
        Document doc = null;
        String title;
        String city;
        Element element;
        Element script;
        String[] str;
        String weather;
        JSONObject jsonObject;
        JSONArray jsonArray;
        List<Weather> list = new ArrayList<Weather>();
        for(int i = 0; i < length; i++){
            //定义URL的规则
            url = "http://www.weather.com.cn/weather/" + cityList[i] + ".shtml";
            //通过URL获取HTML内容
            doc = Jsoup.connect(url).timeout(10000).get();
            //获取title的值
            title = doc.getElementsByTag("title").text();
            city = title.substring(0,2);
            //获取div的class="c7d"的值
            element = doc.select("div.c7d").first();
            //获取第一个<script>标签
            script = element.select("script").first();
            //获取<script>中的内容
            str = script.data().split("=");
            weather = str[1];
            jsonObject = JSONObject.fromObject(weather);
            jsonArray = (JSONArray) jsonObject.get("1d");

            for(int j = 0; j < jsonArray.size();j++){
                String[] strArray = ((String) jsonArray.get(j)).split(",");
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
        }
        return list;
    }
}
