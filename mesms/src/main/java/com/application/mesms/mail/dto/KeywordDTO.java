package com.application.mesms.mail.dto;

import org.springframework.stereotype.Component;

@Component
public class KeywordDTO {

	private String memberId;
	private String emailAddress;
	private String keyword1;
	private String keyword2;
	private String keyword3;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	
	@Override
	public String toString() {
		return "KeywordDTO [memberId=" + memberId + ", emailAddress=" + emailAddress + ", keyword1=" + keyword1
				+ ", keyword2=" + keyword2 + ", keyword3=" + keyword3 + "]";
	}
	
}
