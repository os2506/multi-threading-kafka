package com.oss.demo.eventModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class OrderEvent {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // This will be the primary key in your database

    
    // Order event properties
    private Long orderId;
    private String orderDescription;
    private String status; 

    // Default constructor
    public OrderEvent() {
    }

    // Parameterized constructor
    public OrderEvent(Long orderId, String orderDescription, String status) {
        this.orderId = orderId;
        this.orderDescription = orderDescription;
        this.status = status;
    }

    // Getter and setter methods    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("order_id") // Specify the JSON field name
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
   

    @JsonProperty("order_description") // Another example for a second field
    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderEvent [orderId=" + orderId + ","
				+ " orderDescription=" + orderDescription + ","
						+ " status=" + status + "]";
	}
    
    
}
