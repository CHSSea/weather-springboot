package com.example.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot
 * @description: topic类型MQ的demo
 * @author: Haisheng
 * @create: 2019-04-25 15:07
 **/
@Configuration
public class TopicRabbitConfig {

    private static final Logger logger = LoggerFactory.getLogger(TopicRabbitConfig.class);

    final static String message = "q_topic_message";
    final static String messages = "q_topic_messages";

    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public Queue queueMessages(){
        return new Queue(TopicRabbitConfig.messages);
    }

    /**
     * 声明一个Topic类型的交换机
     * @return
     */
    @Bean
    TopicExchange exchange(){
        return new TopicExchange("mybootexchange");
    }

    /**
     * 绑定Queue到交换机，并指定routingKey
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange){
         return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
