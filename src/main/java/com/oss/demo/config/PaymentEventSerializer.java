package com.oss.demo.config;

import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.demo.eventModel.PaymentEvent;

public class PaymentEventSerializer implements Serializer<PaymentEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, PaymentEvent data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            System.err.println("Error serializing PaymentEvent: " + e.getMessage());
            return new byte[0]; // Return empty array on error
        }
    }
}
