package com.jpmc.javatest.services;

import java.math.BigDecimal;
import java.util.List;

import com.jpmc.javatest.model.Message;
import com.jpmc.javatest.model.Product;

public class MessageService {

	public Message populateMessageType1(String instructionArray[]) {
		Message message = new Message();
		String productType = instructionArray[0];
		BigDecimal rate = new BigDecimal(instructionArray[2].replace("p", ""));

		Product product = new Product();
		product.setProductType(productType);
		product.setValue(rate);
		product.setOriginalValue(rate);
		message.setProduct(product);
		message.setQuantity(1);
		return message;
	}

	public Message populateMessageType2(String instructionArray[]) {
		Message message = new Message();
		int quantity = Integer.parseInt(instructionArray[0]);
		String productType = instructionArray[3];
		BigDecimal rate = new BigDecimal(instructionArray[5].replace("p", ""));

		Product product = new Product();
		product.setProductType(productType.substring(0, productType.length() - 1));
		product.setValue(rate);
		product.setOriginalValue(rate);
		message.setProduct(product);
		message.setQuantity(quantity);
		return message;
	}

	public void addAdjustmentForMessageType3(List<Message> messageList, String productType, BigDecimal newRate, SummaryService summaryService) {
		for (Message message : messageList) {
			Product product = message.getProduct();
			if (product.getProductType().equals(productType)) {
				BigDecimal adjustedValue = product.getValue().add(newRate);
				message.setAdjustedValue(adjustedValue);
				summaryService.updateSummary(message);
			}
		}
	}

	public void subtractAdjustmentForMessageType3(List<Message> messageList, String productType, BigDecimal newRate, SummaryService summaryService) {
		for (Message message : messageList) {
			Product product = message.getProduct();
			if (product.getProductType().equals(productType)) {
				BigDecimal adjustedValue = product.getValue().subtract(newRate);
				message.setAdjustedValue(adjustedValue);
				summaryService.updateSummary(message);
			}
		}
	}

	public void multiplyAdjustmentForMessageType3(List<Message> messageList, String productType, BigDecimal newRate, SummaryService summaryService) {
		for (Message message : messageList) {
			Product product = message.getProduct();
			if (product.getProductType().equals(productType)) {
				BigDecimal adjustedValue = product.getValue().multiply(newRate);
				message.setAdjustedValue(adjustedValue);
				summaryService.updateSummary(message);
			}
		}
	}
}
