package com.example.springboot.rabbitmq.basicmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: springboot
 * @description: 生产者
 * @author: Haisheng
 * @create: 2019-04-18 09:30
 **/
public class SendMessage {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //create connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        //get connection
        Connection connection  = factory.newConnection();
        //申明队列
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "Hello World";
        //向队列发送数据
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.printf(" [x] Sent '" + message + "' ");
        channel.close();
        connection.close();

    }
}
