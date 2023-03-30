package com.application.mesms.project.dto;

import org.springframework.stereotype.Component;

@Component
public class ProjectMemberDTO {

	private long id;
	private long projectCd;
	private String memberId;
	
	
	@Override
	public String toString() {
		return "ProjectMemberDTO [id=" + id + ", projectCd=" + projectCd + ", memberId=" + memberId + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(long projectCd) {
		this.projectCd = projectCd;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
