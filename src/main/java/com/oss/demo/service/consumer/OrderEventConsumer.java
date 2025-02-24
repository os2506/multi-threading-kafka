package com.oss.demo.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.oss.demo.eventModel.OrderEvent;

@Service
public class OrderEventConsumer {
	    
    @KafkaListener(topics = "order_events", groupId = "order_group", containerFactory = "orderKafkaListenerContainerFactory")
    public void handleOrderEvent(OrderEvent orderEvent) {
        System.out.println("Received Order Event: " + orderEvent);
    }

}
