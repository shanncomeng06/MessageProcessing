package com.jpmc.javatest.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jpmc.javatest.enumeration.AdjustmentOperation;
import com.jpmc.javatest.model.Message;
import com.jpmc.javatest.model.Sale;
import com.jpmc.javatest.services.MessageService;
import com.jpmc.javatest.services.SummaryService;

public class MessageProcessingApplication {
	private static final int NO_OF_ATTEMPTS = 50;
	private static final int SUMMARY_LIMIT = 10;
	private static Sale sale = new Sale();
	private static List<Message> messageList = new ArrayList<Message>();
	private static SummaryService summaryService = new SummaryService();
	private static MessageService messageService = new MessageService();

	public static void main(String[] args) {
		int instructionCounter = 0;
		int breakCounter = 1;
		Scanner in = null;
		try {
			while (instructionCounter < NO_OF_ATTEMPTS) {
				System.out.println(">>> Please enter message notification.  <<<<");
				in = new Scanner(System.in);
				String instruction = in.nextLine().trim();
				String instructionArray[] = instruction.split(" ");
				processInstructions(instructionArray);
				instructionCounter++;
				if (breakCounter % SUMMARY_LIMIT == 0) {
					summaryService.printSummary(sale);
				}
				breakCounter++;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("::::::::::::::::: Now the Application is Pausing :::::::::::::::::::::");
			summaryService.printFinalSummary(sale);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				in.close();
			}
		}

	}

	private static void processInstructions(String instructionArray[]) {
		if (instructionArray.length > 0) {
			String messageIdentifier = instructionArray[0];
			if (isNumeric(messageIdentifier)) { // For Message Type 2
				Message message = messageService.populateMessageType2(instructionArray);
				messageList.add(message);
				sale.setMessageList(messageList);
				summaryService.addToSummary(message);
			} else if (isNumeric(instructionArray[2].replace("p", ""))) { // For Message Type 1
				Message message = messageService.populateMessageType1(instructionArray);
				messageList.add(message);
				sale.setMessageList(messageList);
				summaryService.addToSummary(message);
			} else { // For Message Type 3
				String adjustmentOperation = instructionArray[0];
				BigDecimal newRate = new BigDecimal(instructionArray[1].replace("p", ""));
				String productType = instructionArray[2];
				productType = productType.substring(0, productType.length() - 1);
				AdjustmentOperation currentOperation = AdjustmentOperation.fromString(adjustmentOperation.toLowerCase());
				switch (currentOperation) {
				case ADD:
					messageService.addAdjustmentForMessageType3(sale.getMessageList(), productType, newRate, summaryService);
					break;
				case SUBTRACT:
					messageService.subtractAdjustmentForMessageType3(sale.getMessageList(), productType, newRate, summaryService);
					break;
				case MULTIPLY:
					messageService.multiplyAdjustmentForMessageType3(sale.getMessageList(), productType, newRate, summaryService);
					break;
				default:
					throw new AssertionError("Unknown Adjustment Operation " + adjustmentOperation);
				}
			}
		}
	}

	private static boolean isNumeric(String value) {
		boolean result = true;
		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
}
