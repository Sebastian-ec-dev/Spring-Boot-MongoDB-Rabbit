package com.practica_n1.practica.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    // Para crear eventos RabbitMQ
    @Bean
    public DirectExchange pedidoDirectExchange() {
        return new DirectExchange("pedido.created");
    }

    @Bean
    public DirectExchange eliminarDirectExchange() {
        return new DirectExchange("pedido.deleted");
    }


    // Creamos las colas de los eventos
    @Bean
    public Queue pedidoQueue() {
        return new Queue("pedido.created");
    }

    @Bean
    public Queue eliminarQueue() {
        return new Queue("pedido.deleted");
    }


    // Vinculamos la cola con el exchange de eventos
    @Bean
    public Binding bindingPedido(
            Queue pedidoQueue,
            DirectExchange pedidoDirectExchange) {
        return BindingBuilder
                .bind(pedidoQueue)
                .to(pedidoDirectExchange)
                .with("pedido.created") ;
    }

    @Bean
    public Binding bindingEliminar(
            Queue eliminarQueue,
            DirectExchange eliminarDirectExchange) {
        return BindingBuilder
                .bind(eliminarQueue)
                .to(eliminarDirectExchange)
                .with("pedido.deleted") ;
    }
}
