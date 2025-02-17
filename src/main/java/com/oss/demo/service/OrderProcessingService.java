package com.oss.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.concurrent.CompletableFuture;

import com.oss.demo.model.Order;
import com.oss.demo.repository.OrderRepository;


@Service
@RequiredArgsConstructor
public class OrderProcessingService {
    
    private final OrderRepository orderRepository;

    @Async("taskExecutor") // Exécute la tâche en parallèle
    public CompletableFuture<String> processOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        try {
            System.out.println("Processing order #" + orderId + " in thread: " + Thread.currentThread().getName());
            order.setStatus("IN_PROGRESS");
            orderRepository.save(order);

            Thread.sleep(5000); // Simule un traitement long

            order.setStatus("COMPLETED");
            orderRepository.save(order);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return CompletableFuture.completedFuture("Order " + orderId + " processed successfully!");
    }

    public Order createOrder() {
        Order order = new Order();
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }
    
    
}
