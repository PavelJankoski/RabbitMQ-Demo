package com.example.rabbitmqdemo.consumer1.service.impl;

import com.example.rabbitmqdemo.consumer1.service.ConsumerService;
import com.example.rabbitmqdemo.sharedkernel.constants.RabbitMQConstants;
import com.example.rabbitmqdemo.sharedkernel.dto.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @RabbitListener(queues = RabbitMQConstants.QUEUE)
    public void consumeMessage(Message message) {
        if(message.getMessage().equals("bye")){
            throw new RuntimeException();
        }
        else {
            System.out.println(message.getMessage() + ", " + message.getInfo().getTo() +"!");
        }
    }

    @RabbitListener(queues = RabbitMQConstants.DEAD_LETTER_QUEUE)
    public void processFailedMessages(Message message) {
        System.out.println("The consumer said bye!");
    }
}
