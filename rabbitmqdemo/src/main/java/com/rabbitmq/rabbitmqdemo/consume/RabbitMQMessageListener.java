package com.rabbitmq.rabbitmqdemo.consume;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(" Message Recebida = "+ new String(message.getBody()));
    }
}
