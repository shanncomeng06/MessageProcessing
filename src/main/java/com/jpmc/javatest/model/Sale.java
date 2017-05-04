package com.jpmc.javatest.model;

import java.util.ArrayList;
import java.util.List;

public class Sale {
	
	private List<Message> messageList = new ArrayList<Message>();
	
	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
}