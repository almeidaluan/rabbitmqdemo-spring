package com.rabbitmq.rabbitmqdemo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.rabbitmqdemo.domain.model.SimpleMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class RabbitmqdemoApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var array = new ArrayList<SimpleMessage>();
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setName("Teste Name");
        simpleMessage.setDescription("Teste Description");

        SimpleMessage simpleMessage02 = new SimpleMessage();
        simpleMessage02.setName("Teste Name02");
        simpleMessage02.setDescription("Teste Description02");

        array.add(simpleMessage);
        array.add(simpleMessage02);

        rabbitTemplate.convertAndSend("TestExchange","queue-teste-routing-key",array);
    }


}
