package com.oss.demo.service.producer;


import com.oss.demo.eventModel.OrderEvent;
import com.oss.demo.repository.OrderEventRepository;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private static final String TOPIC = "order_events"; // Your Kafka topic
    

    @Autowired
    private OrderEventRepository orderEventRepository;
    
    

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(TOPIC, orderEvent); // Send event to Kafka
    }
    
   
    public void saveOrderEvent(OrderEvent orderEvent) {
        // Save to the database
        orderEventRepository.save(orderEvent);
    }
   
}
