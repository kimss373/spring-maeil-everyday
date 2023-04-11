package com.application.mesms.mail.dto;

import org.springframework.stereotype.Component;

@Component
public class TokenWithMailDTO {

	private String memberId;
	private String accessToken;
	private String refreshToken;
    private String emailAddress;
    private String lastMailId;
    
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getLastMailId() {
		return lastMailId;
	}
	public void setLastMailId(String lastMailId) {
		this.lastMailId = lastMailId;
	}
	
	@Override
	public String toString() {
		return "TokenWithMailDTO [memberId=" + memberId + ", accessToken=" + accessToken + ", refreshToken="
				+ refreshToken + ", emailAddress=" + emailAddress + ", lastMailId=" + lastMailId + "]";
	}

}
