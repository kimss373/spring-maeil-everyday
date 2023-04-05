package com.application.mesms.schedule.dto;

import org.springframework.stereotype.Component;

@Component
public class ScheduleDTO {

	private long scheduleCd;
	private String title;
	private String startDt;
	private String endDt;
	private String memberId;
	private String memo;
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public long getScheduleCd() {
		return scheduleCd;
	}
	public void setScheduleCd(long scheduleCd) {
		this.scheduleCd = scheduleCd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "ScheduleDTO [scheduleCd=" + scheduleCd + ", title=" + title + ", startDt=" + startDt + ", endDt="
				+ endDt + ", memberId=" + memberId + ", memo=" + memo + "]";
	}
	
	
	
}
