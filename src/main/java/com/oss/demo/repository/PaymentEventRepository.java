package com.oss.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oss.demo.eventModel.PaymentEvent;


public interface PaymentEventRepository extends JpaRepository<PaymentEvent, Long> {
    PaymentEvent findByPurchaseId(Long purchaseId);
}
