package com.rabbitmq.rabbitmqdemo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigQueue {

    //Criando fila
    @Bean
    Queue myQueue(){
        return new Queue("MyQueue",true);
    }
    //Criando fila
    @Bean
    Queue myQueue02(){
        return QueueBuilder.durable("MyQueue02")
                .exclusive()
                .build();
    }
}
