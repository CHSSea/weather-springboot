package com.example.springboot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springboot
 * @description: 日期转换工具类
 * @author: Haisheng
 * @create: 2019-11-22 15:35
 **/
public class DateUtil {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Date 类型转 String
     * @param date
     * @return
     */
    public static String date2String(Date date){
         return format.format(date);
    }

    /**
     * String 类型转 Date
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String strDate) throws ParseException {
        return format.parse(strDate);
    }
}
