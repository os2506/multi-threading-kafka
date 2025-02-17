package com.oss.demo.eventModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

@Entity
public class PaymentEvent {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;  // This will be the primary key in your database
    
    
	    private Long purchaseId;
	    private Long orderId;  // The related OrderEvent ID
	    private Double purchaseAmount;
	    private String purchaseCategory; // You can use this to categorize the purchase
	    private String purchaseDate; // Date of the purchase

	    // Default constructor
	    public PaymentEvent() {}

	    // Constructor
	    public PaymentEvent(Long purchaseId, Long orderId, Double purchaseAmount, String purchaseCategory, String purchaseDate) {
	        this.purchaseId = purchaseId;
	        this.orderId = orderId;
	        this.purchaseAmount = purchaseAmount;
	        this.purchaseCategory = purchaseCategory;
	        this.purchaseDate = purchaseDate;
	    }

	    // Getters and setters

	    public Long getPurchaseId() {
	        return purchaseId;
	    }

	    public void setPurchaseId(Long purchaseId) {
	        this.purchaseId = purchaseId;
	    }

	    public Long getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(Long orderId) {
	        this.orderId = orderId;
	    }

	    public Double getPurchaseAmount() {
	        return purchaseAmount;
	    }

	    public void setPurchaseAmount(Double purchaseAmount) {
	        this.purchaseAmount = purchaseAmount;
	    }

	    public String getPurchaseCategory() {
	        return purchaseCategory;
	    }

	    public void setPurchaseCategory(String purchaseCategory) {
	        this.purchaseCategory = purchaseCategory;
	    }

	    public String getPurchaseDate() {
	        return purchaseDate;
	    }

	    public void setPurchaseDate(String purchaseDate) {
	        this.purchaseDate = purchaseDate;
	    }

	    @Override
	    public String toString() {
	        return "PurchaseEvent{" +
	                "purchaseId=" + purchaseId +
	                ", orderId=" + orderId +
	                ", purchaseAmount=" + purchaseAmount +
	                ", purchaseCategory='" + purchaseCategory + '\'' +
	                ", purchaseDate='" + purchaseDate + '\'' +
	                '}';
	    }
	}
