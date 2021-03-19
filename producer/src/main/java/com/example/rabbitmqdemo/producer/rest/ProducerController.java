package com.example.rabbitmqdemo.producer.rest;

import com.example.rabbitmqdemo.sharedkernel.dto.Message;
import com.example.rabbitmqdemo.sharedkernel.dto.SendingInfo;
import com.example.rabbitmqdemo.producer.service.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class ProducerController {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/hello")
    public HttpStatus hello(@RequestBody SendingInfo info){
        this.producerService.publishMessage(info);
        return HttpStatus.OK;
    }
}
