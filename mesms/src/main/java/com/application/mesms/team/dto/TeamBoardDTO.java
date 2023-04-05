package com.application.mesms.team.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TeamBoardDTO {

	private long id;
	private long teamCd;
	private String memberId;
	private String title;
	private String content;
	private long readCnt;
	private Date createDt;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTeamCd() {
		return teamCd;
	}
	public void setTeamCd(long teamCd) {
		this.teamCd = teamCd;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(long readCnt) {
		this.readCnt = readCnt;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	@Override
	public String toString() {
		return "TeamBoardDTO [id=" + id + ", teamCd=" + teamCd + ", memberId=" + memberId + ", title=" + title
				+ ", content=" + content + ", readCnt=" + readCnt + ", createDt=" + createDt + "]";
	}
	
	
}
