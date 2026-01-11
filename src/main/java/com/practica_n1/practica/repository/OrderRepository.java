package com.practica_n1.practica.repository;

import com.practica_n1.practica.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // MongoRepository ya trae métodos CRUD listos
}
