package com.rabbitmq.rabbitmqdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.rabbitmqdemo.consume.RabbitMQMessageListener;
import com.rabbitmq.rabbitmqdemo.consume.RabbitMQMessageListener02;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitMQConfigQueue rabbitMQConfigQueue;

    @Autowired
    private RabbitMQConfigExchange rabbitMQConfigExchange;


    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter(){
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    ConnectionFactory connectionFactory(){
    //        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
    //        cachingConnectionFactory.setUsername("guest");
    //        cachingConnectionFactory.setPassword("guest");

        return new CachingConnectionFactory();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(rabbitMQConfigQueue.myQueue())
                .to(rabbitMQConfigExchange.exampleExchangeDirect())
                .with("queue.routing.key.direct")
                .noargs();

    }

    //Configurando listener01
    @Bean
    MessageListenerContainer messageListener01(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(rabbitMQConfigQueue.myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return simpleMessageListenerContainer;

    }

    //Configurando listener02
    @Bean
    MessageListenerContainer messageListener02(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(rabbitMQConfigQueue.myQueue02());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener02());
        return simpleMessageListenerContainer;
    }
}
