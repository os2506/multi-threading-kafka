package com.oss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.demo.eventModel.PaymentEvent;
import com.oss.demo.service.producer.PaymentEventProducer;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentEventProducer paymentEventProducer;

    
    @PostMapping("/create")
    public ResponseEntity<String> processPayment(@RequestBody PaymentEvent paymentEvent) {
        // Process the received payment event and send it to Kafka
        paymentEventProducer.sendPaymentEvent(paymentEvent);
        
        // Save the payment event to the database
        paymentEventProducer.savePaymentEvent(paymentEvent);
        
        
        
        return ResponseEntity.ok("Payment processed successfully");
    }
}
