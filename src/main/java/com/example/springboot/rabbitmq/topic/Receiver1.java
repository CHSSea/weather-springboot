package com.example.springboot.rabbitmq.topic;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: springboot
 * @description: MQ的消费者
 * @author: Haisheng
 * @create: 2019-04-25 16:07
 **/
@Component
@RabbitListener(queues = "q_topic_message")
public class Receiver1 {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver1 : " + hello);
    }
}
