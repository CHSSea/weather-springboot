package com.example.springboot.rabbitmq.topic;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: springboot
 * @description: MQ的生产者
 * @author: Haisheng
 * @create: 2019-04-23 15:51
 **/
@Component
public class Produce implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * send1方法可以被两个消费者同时接受到
     */
    public void send1(){
        rabbitTemplate.setConfirmCallback(this);
        String context = "Hi, I am message 1";
        this.rabbitTemplate.convertAndSend("mybootexchange","topic.message",context);
    }

    /**
     * send2方法只可以被消费者Receiver2接受到
     */
    public void send2(){
        rabbitTemplate.setConfirmCallback(this);
        String context = "Hi, I am message 2";
        this.rabbitTemplate.convertAndSend("mybootexchange","topic.messages",context);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            System.out.println("消息确认发送成功");
        }else{
            System.out.println("消息确认发送失败" + cause);
        }
    }
}
