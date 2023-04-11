package com.application.mesms.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.mesms.project.dao.ProjectDAO;
import com.application.mesms.project.dto.PojectWorkWithProjectNmDTO;
import com.application.mesms.project.dto.ProjectDTO;
import com.application.mesms.project.dto.ProjectMemberDTO;
import com.application.mesms.project.dto.ProjectSprintDTO;
import com.application.mesms.project.dto.ProjectWorkDTO;
import com.application.mesms.team.dao.TeamDAO;
import com.application.mesms.team.dto.TeamDTO;
import com.application.mesms.team.dto.TeamMemberDTO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private TeamDAO teamDAO;
	
	@Override
	public String generateParticipationCd() throws Exception {
		
		Random random = new Random();
		
		boolean isDuplicated = true;
		String generatedString = "";
		
		while (isDuplicated) {
			
			generatedString = random.ints(97, 122 + 1)
					.limit(15)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			
			if (projectDAO.checkDuplicateParticipationCd1(generatedString) == null) {
				if (projectDAO.checkDuplicateParticipationCd2(generatedString) == null) {
					isDuplicated = false;
				}
			}
			
		}
		
		return generatedString;
	}
	
	@Override
	public int joinProjectOrTeamMember(String participationCd, String memberId) throws Exception{
		
		int result = 0;
		
		ProjectDTO findProjectDTO = projectDAO.selectOneProjectDTOByParticipationCd(participationCd); // 만들어진 프로젝트의 CD 얻어오기
		TeamDTO findTeamDTO = teamDAO.selectOneTeamDTOByParticipationCd(participationCd); // 만들어진 팀의 CD 얻어오기 
		
		if (findProjectDTO != null) {
			
			Map<String, Object> infoMap = new HashMap<String, Object>();
			infoMap.put("projectCd", findProjectDTO.getProjectCd());
			infoMap.put("memberId", memberId);
			ProjectMemberDTO projectMemberDTO = projectDAO.selectOneProjectMemberDTOByMemberId(infoMap);  // 이미 가입한 회원인지?
			
			if (projectMemberDTO == null) {
				
				projectDAO.insertProjectMember(infoMap);
				result = 1;
			}
		} else if (findTeamDTO != null) {
			Map<String, Object> infoMap = new HashMap<String, Object>();
			infoMap.put("teamCd", findTeamDTO.getTeamCd());
			infoMap.put("memberId", memberId);
			TeamMemberDTO teamMemberDTO = teamDAO.selectOneTeamMemberDTOByMemberId(infoMap);  // 이미 가입한 회원인지?
			
			if (teamMemberDTO == null) {
				
				teamDAO.insertTeamMember(infoMap);
				result = 2;
			}
		}
		
		return result;
		
	}

	@Override
	public void createProject(String projectNm, String memberId) throws Exception {
		
		ProjectDTO projectDTO = new ProjectDTO();
		
		String generatedString = generateParticipationCd(); // 랜덤 문자열 생성
		
		projectDTO.setProjectNm(projectNm);
		projectDTO.setMemberId(memberId);
		projectDTO.setParticipationCd(generatedString);
		
		projectDAO.insertNewProject(projectDTO);				// 새 프로젝트 인서트

		joinProjectOrTeamMember(generatedString, memberId);			// 프로젝트 멤버 인서트
		
	}

	@Override
	public List<ProjectDTO> getProjectList(String memberId) throws Exception {
		return projectDAO.selectListProject(memberId);
	}

	@Override
	public boolean checkProjectMember(long projectCd, String memberId) throws Exception {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("projectCd", projectCd);
		infoMap.put("memberId", memberId);
		if (projectDAO.selectOneProjectMember(infoMap) == null) return false;
		else return true;
	}

	@Override
	public List<ProjectMemberDTO> getProjectMemberList(long projectCd) throws Exception {
		return projectDAO.selectListProjectMember(projectCd);
	}

	@Override
	public List<ProjectSprintDTO> getProjectSprintList(long projectCd) throws Exception {
		return projectDAO.selectListProjectSprint(projectCd);
	}

	@Override
	public List<ProjectWorkDTO> getProjectWorkList(long projectCd) throws Exception {
		return projectDAO.selectListProjectWork(projectCd);
	}

	@Override
	public void createBacklog(ProjectWorkDTO projectWorkDTO) throws Exception {
		Long largestNum  = projectDAO.selectOneLargestNumInBacklog(projectWorkDTO.getProjectCd());
		if (largestNum == null) {
			projectWorkDTO.setProjectWorkNum(1);
		}
		else {
			
			projectWorkDTO.setProjectWorkNum((long)largestNum + 1);
		}
		projectDAO.insertBacklog(projectWorkDTO);
	}

	@Override
	public ProjectDTO getProjectDTO(long projectCd) throws Exception {
		return projectDAO.selectOneProjectDTOByProjectCd(projectCd);
	}

	@Override
	public void createSprint(long projectCd) throws Exception {
		
		ProjectSprintDTO projectSprintDTO = new ProjectSprintDTO();
		
		projectSprintDTO.setProjectCd(projectCd);
		
		Long largestNum  = projectDAO.selectOneLargestNumInSprint(projectCd);
		
		if (largestNum == null) {
			projectSprintDTO.setNum(1);
		}
		else {
			
			projectSprintDTO.setNum((long)largestNum + 1);
		}
		
		projectDAO.insertSprint(projectSprintDTO);
	}

	@Override
	public ProjectWorkDTO getProjectWorkDTOById(long id) throws Exception {
		return projectDAO.selectOneProjectWorkDTOById(id);
	}

	@Override
	public void modifyBacklog(ProjectWorkDTO projectWorkDTO) throws Exception {
		projectDAO.updateBacklog(projectWorkDTO);
	}

	@Override
	public ProjectSprintDTO getProjectSprintDTOById(long id) throws Exception {
		return projectDAO.selectOneProjectSprintDTOById(id);
	}

	@Override
	public void modifySprint(ProjectSprintDTO projectSprintDTO) throws Exception {
		projectDAO.updateSprint(projectSprintDTO);
	}

	@Override
	public Map<String, Object> getProjectBoardMap(long projectCd) throws Exception {
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		
		List<ProjectWorkDTO> workinSprintList = projectDAO.selectListWorkinSprintList(projectCd);
		List<ProjectWorkDTO> todoList = new ArrayList<ProjectWorkDTO>();
		List<ProjectWorkDTO> inprogressList = new ArrayList<ProjectWorkDTO>();
		List<ProjectWorkDTO> doneList = new ArrayList<ProjectWorkDTO>();
		
		for (int i = 0 ; i < workinSprintList.size() ; i++) {
			if (workinSprintList.get(i).getTodoCondition().equals("TODO")) {
				todoList.add(workinSprintList.get(i));
			} else if (workinSprintList.get(i).getTodoCondition().equals("inProgress")){
				inprogressList.add(workinSprintList.get(i));
			} else {
				doneList.add(workinSprintList.get(i));
			}
		}
		boardMap.put("todoList", todoList);
		boardMap.put("inprogressList", inprogressList);
		boardMap.put("doneList", doneList);
		
		return boardMap;
	}

	@Override
	public void changeTodoCondition(long id, String todoCondition) throws Exception {
		
		ProjectWorkDTO projectWorkDTO = new ProjectWorkDTO();
		projectWorkDTO.setId(id);
		projectWorkDTO.setTodoCondition(todoCondition);
		
		projectDAO.updateTodoCondition(projectWorkDTO);
		
	}
	
	@Override
	public List<Object> getChartList(long projectCd) throws Exception {
		
		List<ProjectWorkDTO> projectWorkList = projectDAO.selectListProjectWork(projectCd);
		List<ProjectSprintDTO> projectSprintList = projectDAO.selectListProjectSprint(projectCd);
		
		List<Object> list = new ArrayList<Object>();
		
		for (int i = 0 ; i < projectSprintList.size() ; i++) {
			
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			ProjectSprintDTO projectSprintDTO = projectSprintList.get(i);
			
			tmpMap.put("sprintNm", projectSprintDTO.getNum() + " " + projectSprintDTO.getSprintNm());
			tmpMap.put("TODO", 0);
			tmpMap.put("inProgress", 0);
			tmpMap.put("Done", 0);
			tmpMap.put("total", 0);
			
			if (projectSprintDTO.getDone().equals("Y")) {
				tmpMap.put("sprintDone", "완료된 스프린트");
			}
			
			
			for(int j = 0 ; j < projectWorkList.size() ; j++) {
				
				ProjectWorkDTO projectWorkDTO = projectWorkList.get(j);
				
				if (projectWorkDTO.getProjectSprintId() != projectSprintDTO.getId()) {
					continue;
				}
				
				tmpMap.put(projectWorkDTO.getTodoCondition(), Long.valueOf(String.valueOf(tmpMap.get(projectWorkDTO.getTodoCondition()))) + 1);
				tmpMap.put("total", Long.valueOf(String.valueOf(tmpMap.get("total"))) + 1);
			}
			list.add(tmpMap);
			
		}
		
		return list;
	}

	@Override
	public void modifyProjectSetting(ProjectDTO projectDTO) throws Exception {
		projectDAO.updateProjectSetting(projectDTO);
	}

	@Override
	public List<PojectWorkWithProjectNmDTO> getMyProjectWorkList(String memberId) throws Exception {
		return projectDAO.selectListMyProjectWork(memberId);
	}
	

	
	
}
