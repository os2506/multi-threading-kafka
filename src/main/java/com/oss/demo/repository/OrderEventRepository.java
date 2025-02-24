package com.oss.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oss.demo.eventModel.OrderEvent;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
    OrderEvent findByOrderId(Long orderId);
}
