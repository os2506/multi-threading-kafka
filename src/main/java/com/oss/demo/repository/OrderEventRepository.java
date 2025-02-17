package com.oss.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oss.demo.eventModel.OrderEvent;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
    // You can add custom query methods if needed, e.g., find by orderId
    OrderEvent findByOrderId(Long orderId);
}
