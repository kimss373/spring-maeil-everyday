package com.application.mesms.team.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class TeamMemberWithProfileDTO {

	private long id;
	private long teamCd;
	private String memberId;
	private Date joinDt;
	private String profileImage;
	private String memberNm;
	
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
	public Date getJoinDt() {
		return joinDt;
	}
	public void setJoinDt(Date joinDt) {
		this.joinDt = joinDt;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	@Override
	public String toString() {
		return "TeamMemberWithProfileDTO [id=" + id + ", teamCd=" + teamCd + ", memberId=" + memberId + ", joinDt="
				+ joinDt + ", profileImage=" + profileImage + ", memberNm=" + memberNm + "]";
	}
	
	
}
