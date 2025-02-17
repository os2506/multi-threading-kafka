package com.oss.demo.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.oss.demo.eventModel.PaymentEvent;
import com.oss.demo.repository.PaymentEventRepository;


@Service
public class PaymentEventProducer {
   
	private static final String TOPIC = "payment_events";
	
    @Autowired
    private PaymentEventRepository paymentEventRepository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void sendPaymentEvent(PaymentEvent paymentEvent) {
        kafkaTemplate.send(TOPIC, paymentEvent);
    }
    
    public void savePaymentEvent(PaymentEvent paymentEvent) {
        // Save to the database
    	paymentEventRepository.save(paymentEvent);
    }
}
