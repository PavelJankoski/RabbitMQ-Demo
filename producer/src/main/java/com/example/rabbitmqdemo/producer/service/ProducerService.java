package com.example.rabbitmqdemo.producer.service;

import com.example.rabbitmqdemo.sharedkernel.dto.Message;
import com.example.rabbitmqdemo.sharedkernel.dto.SendingInfo;

public interface ProducerService {
    void publishMessage(SendingInfo info);
}
