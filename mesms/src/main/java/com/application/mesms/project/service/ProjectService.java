package com.application.mesms.project.service;

import java.util.List;
import java.util.Map;

import com.application.mesms.project.dto.ProjectDTO;
import com.application.mesms.project.dto.ProjectMemberDTO;
import com.application.mesms.project.dto.ProjectSprintDTO;
import com.application.mesms.project.dto.ProjectWorkDTO;

public interface ProjectService {
	
	public String generateParticipationCd() throws Exception;
	public int joinProjectOrTeamMember(String participationCd, String memberId) throws Exception;
	public void createProject(String projectNm, String memberId) throws Exception;
	public List<ProjectDTO> getProjectList(String memberId) throws Exception;
	public boolean checkProjectMember(long projectCd, String memberId) throws Exception;
	public List<ProjectMemberDTO> getProjectMemberList(long projectCd) throws Exception;
	public List<ProjectSprintDTO> getProjectSprintList(long projectCd) throws Exception;
	public List<ProjectWorkDTO> getProjectWorkList(long projectCd) throws Exception;
	public void createBacklog(ProjectWorkDTO projectWorkDTO) throws Exception;
	public ProjectDTO getProjectDTO(long projectCd) throws Exception;
	public void createSprint(long projectCd) throws Exception;
	public ProjectWorkDTO getProjectWorkDTOById(long id) throws Exception;
	public void modifyBacklog(ProjectWorkDTO projectWorkDTO) throws Exception;
	public ProjectSprintDTO getProjectSprintDTOById(long id) throws Exception;
	public void modifySprint(ProjectSprintDTO projectSprintDTO) throws Exception;
	public Map<String, Object> getProjectBoardMap(long projectCd) throws Exception;
	public void changeTodoCondition(long id, String todoCondition) throws Exception;
	public List<Object> getChartList(long projectCd) throws Exception;
	public void modifyProjectSetting(ProjectDTO projectDTO) throws Exception;
	
}
