package com.oss.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oss.demo.eventModel.PaymentEvent;



public interface PaymentEventRepository extends JpaRepository<PaymentEvent, Long> {
    // You can add custom query methods if needed, e.g., find by orderId
    PaymentEvent findByPurchaseId(Long purchaseId);
}