package com.application.mesms.project.dto;

import org.springframework.stereotype.Component;

@Component
public class ProjectSprintDTO {

	private long id;
	private long num;
	private long projectCd;
	private String sprintNm;
	private String done;
	
	
	@Override
	public String toString() {
		return "ProjectSprintDTO [id=" + id + ", num=" + num + ", projectCd=" + projectCd + ", sprintNm=" + sprintNm
				+ ", done=" + done + "]";
	}
	
	
	public long getNum() {
		return num;
	}


	public void setNum(long num) {
		this.num = num;
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
	public String getSprintNm() {
		return sprintNm;
	}
	public void setSprintNm(String sprintNm) {
		this.sprintNm = sprintNm;
	}
	public String getDone() {
		return done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	
}
