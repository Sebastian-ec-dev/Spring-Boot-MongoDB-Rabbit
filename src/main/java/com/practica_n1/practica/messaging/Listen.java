package com.practica_n1.practica.messaging;

import com.practica_n1.practica.domain.Order;
import com.practica_n1.practica.service.JsonService;
import com.practica_n1.practica.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Listen {
    private final JsonService jsonService;
    private final OrderService orderService;

    @RabbitListener(queues = "pedido.created")
    public void listen(String message){
        try {
            Thread.sleep(13000);
            Order order = jsonService.fromJson(message, Order.class);
            orderService.deleteOrder(order);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "pedido.deleted")
    public void listenDelete(String message){
        Order order = jsonService.fromJson(message, Order.class);
        System.out.println("Pedido eliminado: " + order.getId());
    }

}
