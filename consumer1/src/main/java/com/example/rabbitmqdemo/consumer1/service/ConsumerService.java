package com.example.rabbitmqdemo.consumer1.service;


import com.example.rabbitmqdemo.sharedkernel.dto.Message;

public interface ConsumerService {
    public void consumeMessage(Message message);
}
