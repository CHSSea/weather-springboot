package com.example.springboot.rabbitmq.topic;


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: springboot
 * @description: MQ的消费者
 * @author: Haisheng
 * @create: 2019-04-25 16:07
 **/
@Component
public class Receiver1 {

    private static final Logger logger = LoggerFactory.getLogger(Receiver1.class);

    @RabbitListener(queues = "q_topic_message")
    public void process(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        System.out.println(new String(message.getBody()));
        logger.debug("receive: " + new String(message.getBody()));
    }
}
