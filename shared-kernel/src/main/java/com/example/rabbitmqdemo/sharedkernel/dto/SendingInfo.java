package com.example.rabbitmqdemo.sharedkernel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SendingInfo {
    private String from;
    private String to;
}
