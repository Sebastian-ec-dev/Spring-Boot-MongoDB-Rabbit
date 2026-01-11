package com.practica_n1.practica.controller;

import com.practica_n1.practica.domain.Order;
import com.practica_n1.practica.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> listOrders(){
        return orderService.listOrders();
    }

}
