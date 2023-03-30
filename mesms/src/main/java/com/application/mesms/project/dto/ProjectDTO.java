package com.application.mesms.project.dto;

import org.springframework.stereotype.Component;

@Component
public class ProjectDTO {
	
	private long projectCd;
	private String projectNm;
	private String memberId;
	private String participationCd;

	public long getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(long projectCd) {
		this.projectCd = projectCd;
	}
	public String getProjectNm() {
		return projectNm;
	}
	public void setProjectNm(String projectNm) {
		this.projectNm = projectNm;
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
		return "ProjectDTO [projectCd=" + projectCd + ", projectNm=" + projectNm + ", memberId=" + memberId
				+ ", participationCd=" + participationCd + "]";
	}
	
	
}
