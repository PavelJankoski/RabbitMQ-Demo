package com.example.rabbitmqdemo.producer.config;

import com.example.rabbitmqdemo.sharedkernel.constants.RabbitMQConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(RabbitMQConstants.DEAD_LETTER_EXCHANGE);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(RabbitMQConstants.EXCHANGE);
    }

    @Bean
    Queue dlq() {
        return QueueBuilder.durable(RabbitMQConstants.DEAD_LETTER_QUEUE).build();
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(RabbitMQConstants.QUEUE).withArgument("x-dead-letter-exchange", RabbitMQConstants.DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", RabbitMQConstants.DEAD_LETTER_ROUTING_KEY).build();
    }

    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with(RabbitMQConstants.DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(RabbitMQConstants.ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}