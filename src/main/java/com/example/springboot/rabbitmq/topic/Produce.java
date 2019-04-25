package com.example.springboot.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: springboot
 * @description: MQ的生产者
 * @author: Haisheng
 * @create: 2019-04-23 15:51
 **/
@Component
public class Produce {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * send1方法可以被两个消费者同时接受到
     */
    public void send1(){
        String context = "Hi, I am message 1";
        this.rabbitTemplate.convertAndSend("mybootexchange","topic.message",context);
    }

    /**
     * send2方法只可以被消费者Receiver2接受到
     */
    public void send2(){
        String context = "Hi, I am message 2";
        this.rabbitTemplate.convertAndSend("mybootexchange","topic.messages",context);
    }
}
