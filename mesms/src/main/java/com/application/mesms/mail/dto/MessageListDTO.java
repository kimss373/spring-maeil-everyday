package com.application.mesms.mail.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessageListDTO {

	private List<MessageDTO> messages;
    private String nextPageToken;
    private int resultSizeEstimate;
    
	public List<MessageDTO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public int getResultSizeEstimate() {
		return resultSizeEstimate;
	}
	public void setResultSizeEstimate(int resultSizeEstimate) {
		this.resultSizeEstimate = resultSizeEstimate;
	}
	
	@Override
	public String toString() {
		return "MessageListDTO [messages=" + messages + ", nextPageToken=" + nextPageToken + ", resultSizeEstimate="
				+ resultSizeEstimate + "]";
	}
	
}
