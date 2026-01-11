package com.practica_n1.practica.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practica_n1.practica.domain.Order;
import com.practica_n1.practica.messaging.CreatedPublisher;
import com.practica_n1.practica.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CreatedPublisher createdPublisher;
    private final JsonService jsonService;

    // Crear Pedido
    public Order createOrder(Order order) {

        try {
            Order savedOrder = orderRepository.save(order);
            createdPublisher.publish(jsonService.toJson(savedOrder));
            return savedOrder;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Listar Pedidos
    public List<Order> listOrders(){
        return orderRepository.findAll();
    }


    // Eliminar Pedido
    public void deleteOrder(Order order) {
        try {
            createdPublisher.publishDelete(jsonService.toJson(order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        orderRepository.delete(order);
    }
}
