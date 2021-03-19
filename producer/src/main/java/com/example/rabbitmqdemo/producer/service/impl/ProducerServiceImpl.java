package com.example.rabbitmqdemo.producer.service.impl;

import com.example.rabbitmqdemo.sharedkernel.constants.RabbitMQConstants;
import com.example.rabbitmqdemo.sharedkernel.dto.Message;
import com.example.rabbitmqdemo.sharedkernel.dto.SendingInfo;
import com.example.rabbitmqdemo.producer.service.ProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final RabbitTemplate template;

    public ProducerServiceImpl(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void publishMessage(SendingInfo info) {
        Message message = new Message(info, "Hello");
        template.convertAndSend(RabbitMQConstants.EXCHANGE, RabbitMQConstants.ROUTING_KEY, message);
    }
}
