package com.example.springboot.test;

import com.example.springboot.rabbitmq.topic.Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @program: springboot
 * @description: topicMQ的测试类
 * @author: Haisheng
 * @create: 2019-04-25 16:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTopicTest {

    @Autowired
    private Produce produce;

    @Test
    public void send1(){
        produce.send1();
    }

    @Test
    public void send2(){
        produce.send2();
    }
}
