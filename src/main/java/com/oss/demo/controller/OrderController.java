package com.oss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oss.demo.eventModel.OrderEvent;
import com.oss.demo.repository.OrderRepository;
import com.oss.demo.service.OrderProcessingService;
import com.oss.demo.service.producer.OrderEventProducer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
	
    @Autowired
    private OrderEventProducer orderEventProducer;

    private final OrderProcessingService orderProcessingService;
    
    private final OrderRepository orderRepository;

    /*
    // Créer une nouvelle commande
    // curl -X POST http://localhost:8080/orders
    @PostMapping
    public Order createOrder() {
        return orderProcessingService.createOrder();
    }
    */
    
    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent orderEvent) {
        // Process the received order event and send it to Kafka
        orderEventProducer.sendOrderEvent(orderEvent);
        
        // Save the order event to the database
        orderEventProducer.saveOrderEvent(orderEvent);
      
        return ResponseEntity.ok("Order created successfully");
    }
    

    // Traiter une commande en multithreading
    // curl -X POST http://localhost:8080/orders/1
    @PostMapping("/{orderId}")
    public CompletableFuture<String> processOrder(@PathVariable Long orderId) {
        return orderProcessingService.processOrder(orderId);
    }
    
    // Traiter plusieurs commande à la fois 
    /*
    INSERT INTO orders (id, status) VALUES 
    (2, 'PENDING'), 
    (3, 'PENDING'), 
    (4, 'PENDING'), 
    (5, 'PENDING');
    */
    //curl -X POST http://localhost:8080/orders/batch -H "Content-Type: application/json" -d "[2, 3, 4, 5]"
    @PostMapping("/batch")
    public CompletableFuture<String> processMultipleOrders(@RequestBody List<Long> orderIds) {
        List<Long> missingOrders = orderIds.stream()
                .filter(id -> !orderRepository.existsById(id))
                .toList();

        if (!missingOrders.isEmpty()) {
            throw new RuntimeException("Orders not found: " + missingOrders);
        }

        List<CompletableFuture<String>> futures = orderIds.stream()
                .map(orderProcessingService::processOrder)
                .toList();

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> "All orders processed!");
    }
}

