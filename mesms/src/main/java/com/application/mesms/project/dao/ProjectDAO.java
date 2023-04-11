package com.application.mesms.project.dao;

import java.util.List;
import java.util.Map;

import com.application.mesms.project.dto.PojectWorkWithProjectNmDTO;
import com.application.mesms.project.dto.ProjectDTO;
import com.application.mesms.project.dto.ProjectMemberDTO;
import com.application.mesms.project.dto.ProjectSprintDTO;
import com.application.mesms.project.dto.ProjectWorkDTO;
import com.application.mesms.team.dto.TeamDTO;

public interface ProjectDAO {
	
	public ProjectDTO checkDuplicateParticipationCd1(String generatedString) throws Exception;
	public TeamDTO checkDuplicateParticipationCd2(String generatedString) throws Exception;
	public void insertNewProject(ProjectDTO projectDTO) throws Exception;
	public ProjectDTO selectOneProjectDTOByParticipationCd(String participationCd) throws Exception;
	public ProjectMemberDTO selectOneProjectMemberDTOByMemberId(Map<String, Object> infoMap) throws Exception;
	public void insertProjectMember(Map<String,Object> infoMap) throws Exception;
	public List<ProjectDTO> selectListProject(String memberId) throws Exception;
	public ProjectMemberDTO selectOneProjectMember(Map<String,Object> infoMap) throws Exception;
	public List<ProjectMemberDTO> selectListProjectMember(long projectCd) throws Exception;
	public List<ProjectSprintDTO> selectListProjectSprint(long projectCd) throws Exception;
	public List<ProjectWorkDTO> selectListProjectWork(long projectCd) throws Exception;
	public void insertBacklog(ProjectWorkDTO projectWorkDTO) throws Exception;
	public Long selectOneLargestNumInBacklog(long projectCd) throws Exception;
	public ProjectDTO selectOneProjectDTOByProjectCd(long projectCd) throws Exception;
	public Long selectOneLargestNumInSprint(long projectCd) throws Exception;
	public void insertSprint(ProjectSprintDTO projectSprintDTO) throws Exception;
	public ProjectWorkDTO selectOneProjectWorkDTOById(long id) throws Exception;
	public void updateBacklog(ProjectWorkDTO projectWorkDTO) throws Exception;
	public ProjectSprintDTO selectOneProjectSprintDTOById(long id) throws Exception;
	public void updateSprint(ProjectSprintDTO projectSprintDTO) throws Exception;
	public List<ProjectWorkDTO> selectListWorkinSprintList(long projectCd) throws Exception;
	public void updateTodoCondition(ProjectWorkDTO projectWorkDTO) throws Exception;
	public void updateProjectSetting(ProjectDTO projectDTO) throws Exception;
	public List<PojectWorkWithProjectNmDTO> selectListMyProjectWork(String memberId) throws Exception;
	
}
