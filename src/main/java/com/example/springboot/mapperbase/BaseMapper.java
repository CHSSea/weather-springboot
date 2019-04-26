package com.example.springboot.mapperbase;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @program: springboot
 * @description: 自定义Mapper的基类,不能放到扫描的mapper包下面
 * @author: Haisheng
 * @create: 2019-04-26 10:37
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
