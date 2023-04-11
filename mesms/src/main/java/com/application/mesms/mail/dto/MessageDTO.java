package com.application.mesms.mail.dto;

import org.springframework.stereotype.Component;

@Component
public class MessageDTO {

	private String id;
	private String threadId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	@Override
	public String toString() {
		return "MessageDTO [id=" + id + ", threadId=" + threadId + "]";
	}
	
}
