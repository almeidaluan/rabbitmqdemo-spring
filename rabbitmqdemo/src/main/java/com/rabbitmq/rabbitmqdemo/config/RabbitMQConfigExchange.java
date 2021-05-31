package com.rabbitmq.rabbitmqdemo.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigExchange {

    @Bean
    Exchange exampleExchange(){
        return new TopicExchange("ExampleExchange");
    }

    @Bean
    Exchange exampleExchangeDirect(){
        return ExchangeBuilder.directExchange("Example2ndExchange")
                .autoDelete()
                .internal()
                .build();
    }

    @Bean
    Exchange exampleExchangeTopic(){
        return ExchangeBuilder.topicExchange("ExampleTopicExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    Exchange exampleExchangeFanout(){
        return ExchangeBuilder.fanoutExchange("ExampleFanoutExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    Exchange exampleExchangeHeaders(){
        return ExchangeBuilder.headersExchange("ExampleHeadersExchange")
                .internal()
                .durable(true)
                .ignoreDeclarationExceptions()
                .build();
    }
}
