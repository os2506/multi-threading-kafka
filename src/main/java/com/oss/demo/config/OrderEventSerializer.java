package com.oss.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.demo.eventModel.OrderEvent;

import org.apache.kafka.common.serialization.Serializer;


public class OrderEventSerializer implements Serializer<OrderEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, OrderEvent data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            System.err.println("Error serializing PaymentEvent: " + e.getMessage());
            return new byte[0]; // Return empty array on error
        }
    }
}