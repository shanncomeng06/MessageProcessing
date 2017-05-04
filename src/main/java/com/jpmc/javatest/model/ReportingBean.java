package com.jpmc.javatest.model;

import java.math.BigDecimal;

public class ReportingBean {

	private String productType;
	private BigDecimal value;
	private BigDecimal adjustedValue;
	private int quantity;
	private BigDecimal rate;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getAdjustedValue() {
		return adjustedValue;
	}

	public void setAdjustedValue(BigDecimal adjustedValue) {
		this.adjustedValue = adjustedValue;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
