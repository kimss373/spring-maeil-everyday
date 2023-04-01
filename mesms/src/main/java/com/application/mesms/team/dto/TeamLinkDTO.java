package com.application.mesms.team.dto;

import org.springframework.stereotype.Component;

@Component
public class TeamLinkDTO {

	private long id;
	private long teamCd;
	private String memberId;
	private String link;
	private String content;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "TeamLinkDTO [id=" + id + ", teamCd=" + teamCd + ", memberId=" + memberId + ", link=" + link
				+ ", content=" + content + "]";
	}
	
	
}
