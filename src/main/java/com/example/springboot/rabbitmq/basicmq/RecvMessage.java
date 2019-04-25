package com.example.springboot.rabbitmq.basicmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * @program: springboot
 * @description: 消费者
 * @author: Haisheng
 * @create: 2019-04-18 09:40
 **/
public class RecvMessage {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        while (true){
            channel.basicConsume(QUEUE_NAME,false,"",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,AMQP.BasicProperties properties,byte[] body) throws UnsupportedEncodingException {
                    System.out.printf(new String(body,"UTF-8"));
                }
            });
        }
    }
}
