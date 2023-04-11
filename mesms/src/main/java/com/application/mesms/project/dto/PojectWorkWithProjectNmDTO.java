package com.application.mesms.project.dto;

import org.springframework.stereotype.Component;

@Component
public class PojectWorkWithProjectNmDTO {

	private long id;
	private long projectWorkNum;
	private long projectCd;
	private String issue; 
	private long projectSprintId;
	private String todoCondition;
	private String responsibility;
	private String projectNm;
	
	@Override
	public String toString() {
		return "PojectWorkWithProjectNmDTO [id=" + id + ", projectWorkNum=" + projectWorkNum + ", projectCd="
				+ projectCd + ", issue=" + issue + ", projectSprintId=" + projectSprintId + ", todoCondition="
				+ todoCondition + ", responsibility=" + responsibility + ", projectNm=" + projectNm + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProjectWorkNum() {
		return projectWorkNum;
	}
	public void setProjectWorkNum(long projectWorkNum) {
		this.projectWorkNum = projectWorkNum;
	}
	public long getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(long projectCd) {
		this.projectCd = projectCd;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public long getProjectSprintId() {
		return projectSprintId;
	}
	public void setProjectSprintId(long projectSprintId) {
		this.projectSprintId = projectSprintId;
	}
	public String getTodoCondition() {
		return todoCondition;
	}
	public void setTodoCondition(String todoCondition) {
		this.todoCondition = todoCondition;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getProjectNm() {
		return projectNm;
	}
	public void setProjectNm(String projectNm) {
		this.projectNm = projectNm;
	}
	
}
