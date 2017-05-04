package com.jpmc.javatest.model;

import java.math.BigDecimal;

public class Message {
	
	private Product product;
	private int quantity;
	private BigDecimal totalAmount;
	private BigDecimal adjustedValue;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getAdjustedValue() {
		return adjustedValue;
	}
	public void setAdjustedValue(BigDecimal adjustedValue) {
		this.adjustedValue = adjustedValue;
	}
}