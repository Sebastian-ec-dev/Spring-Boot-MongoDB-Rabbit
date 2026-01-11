package com.practica_n1.practica.messaging;

import com.practica_n1.practica.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatedPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(String message){
        rabbitTemplate.convertAndSend("pedido.created","pedido.created", message);
    }

    public void publishDelete(String message){
        rabbitTemplate.convertAndSend("pedido.deleted","pedido.deleted", message);
    }

}
