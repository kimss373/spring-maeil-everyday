package com.application.mesms.team.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class TeamMemberDTO {

	private long id;
	private long teamCd;
	private String memberId;
	private Date joinDt;
	
	public Date getJoinDt() {
		return joinDt;
	}
	public void setJoinDt(Date joinDt) {
		this.joinDt = joinDt;
	}
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
	
	@Override
	public String toString() {
		return "TeamMemberDTO [id=" + id + ", teamCd=" + teamCd + ", memberId=" + memberId + ", joinDt=" + joinDt + "]";
	}
	
	
	
}
