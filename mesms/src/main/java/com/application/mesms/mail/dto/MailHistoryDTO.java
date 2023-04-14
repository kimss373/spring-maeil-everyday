package com.application.mesms.mail.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MailHistoryDTO {

	private long id;
	private String memberId;
	private String keyword;
	private Date historyDt;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Date getHistoryDt() {
		return historyDt;
	}
	public void setHistoryDt(Date historyDt) {
		this.historyDt = historyDt;
	}
	
	@Override
	public String toString() {
		return "MailHistoryDTO [id=" + id + ", memberId=" + memberId + ", keyword=" + keyword + ", historyDt="
				+ historyDt + "]";
	}
	
}
