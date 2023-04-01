package com.application.mesms.team.dto;

import org.springframework.stereotype.Component;

@Component
public class TeamDTO {

	private long teamCd;
	private String teamNm;
	private String memberId;
	private String participationCd;
	private String teamAnnouncement;
	private String teamTarget;
	
	public String getTeamAnnouncement() {
		return teamAnnouncement;
	}
	public void setTeamAnnouncement(String teamAnnouncement) {
		this.teamAnnouncement = teamAnnouncement;
	}
	public String getTeamTarget() {
		return teamTarget;
	}
	public void setTeamTarget(String teamTarget) {
		this.teamTarget = teamTarget;
	}
	public long getTeamCd() {
		return teamCd;
	}
	public void setTeamCd(long teamCd) {
		this.teamCd = teamCd;
	}
	public String getTeamNm() {
		return teamNm;
	}
	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getParticipationCd() {
		return participationCd;
	}
	public void setParticipationCd(String participationCd) {
		this.participationCd = participationCd;
	}
	
	@Override
	public String toString() {
		return "TeamDTO [teamCd=" + teamCd + ", teamNm=" + teamNm + ", memberId=" + memberId + ", participationCd="
				+ participationCd + ", teamAnnouncement=" + teamAnnouncement + ", teamTarget=" + teamTarget + "]";
	}
	
}
