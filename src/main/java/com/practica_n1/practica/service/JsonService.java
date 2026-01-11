package com.practica_n1.practica.service;

import com.practica_n1.practica.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;


@RequiredArgsConstructor
@Service
public class JsonService {
    private final ObjectMapper objectMapper;
    public String toJson(Order order) throws JsonProcessingException {
        return objectMapper.writeValueAsString(order);
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error convirtiendo JSON", e);
        }
    }

}
