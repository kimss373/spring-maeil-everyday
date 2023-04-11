package com.application.mesms.mail.dto;

import org.springframework.stereotype.Component;

@Component
public class MailDTO {

	private String emailAddress;
    private String messagesTotal;
    private String threadsTotal;
    private String historyId;
    
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getMessagesTotal() {
		return messagesTotal;
	}
	public void setMessagesTotal(String messagesTotal) {
		this.messagesTotal = messagesTotal;
	}
	public String getThreadsTotal() {
		return threadsTotal;
	}
	public void setThreadsTotal(String threadsTotal) {
		this.threadsTotal = threadsTotal;
	}
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	@Override
	public String toString() {
		return "MailDTO [emailAddress=" + emailAddress + ", messagesTotal=" + messagesTotal + ", threadsTotal="
				+ threadsTotal + ", historyId=" + historyId + "]";
	}
    
}
