package com.application.mesms.mail.dto;

import org.springframework.stereotype.Component;

@Component
public class TokenDTO {

	private String access_token;
    private Long expires_in;
    private String refresh_token;
    private String scope;
    private String token_type;
    
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	
	@Override
	public String toString() {
		return "TokenDTO [access_token=" + access_token + ", expires_in=" + expires_in + ", refresh_token="
				+ refresh_token + ", scope=" + scope + ", token_type=" + token_type + ", getAccess_token()="
				+ getAccess_token() + ", getExpires_in()=" + getExpires_in() + ", getRefresh_token()="
				+ getRefresh_token() + ", getScope()=" + getScope() + ", getToken_type()=" + getToken_type()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
