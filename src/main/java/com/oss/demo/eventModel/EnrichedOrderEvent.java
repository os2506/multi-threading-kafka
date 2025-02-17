package com.oss.demo.eventModel;

public class EnrichedOrderEvent {

    private OrderEvent order;
    private PaymentEvent payment;

    // Constructor
    public EnrichedOrderEvent(OrderEvent order, PaymentEvent payment) {
        this.order = order;
        this.payment = payment;
    }

	public OrderEvent getOrder() {
		return order;
	}

	public void setOrder(OrderEvent order) {
		this.order = order;
	}

	public PaymentEvent getPayment() {
		return payment;
	}

	public void setPayment(PaymentEvent payment) {
		this.payment = payment;
	}

}

