package com.application.mesms.team.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.mesms.project.dto.ProjectDTO;
import com.application.mesms.project.dto.ProjectMemberDTO;
import com.application.mesms.project.service.ProjectService;
import com.application.mesms.team.dao.TeamDAO;
import com.application.mesms.team.dto.TeamDTO;
import com.application.mesms.team.dto.TeamLinkDTO;
import com.application.mesms.team.dto.TeamMemberDTO;
import com.application.mesms.team.dto.TeamMemberWithProfileDTO;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamDAO teamDAO;
	
	@Autowired
	private ProjectService projectService;
	
	@Override
	public boolean joinTeamMember(String participationCd, String memberId) throws Exception{
		
		boolean result = false;
		
		TeamDTO findTeamDTO = teamDAO.selectOneTeamDTOByParticipationCd(participationCd); // 만들어진 팀의 CD 얻어오기
		
		if (findTeamDTO != null) {
			
			Map<String, Object> infoMap = new HashMap<String, Object>();
			infoMap.put("teamCd", findTeamDTO.getTeamCd());
			infoMap.put("memberId", memberId);
			TeamMemberDTO teamMemberDTO = teamDAO.selectOneTeamMemberDTOByMemberId(infoMap);  // 이미 가입한 회원인지?
			
			if (teamMemberDTO == null) {
				
				teamDAO.insertTeamMember(infoMap);
				result = true;
			}
		}
		
		return result;
		
	}

	@Override
	public void createTeam(String teamNm, String memberId) throws Exception {
		
		TeamDTO teamDTO = new TeamDTO();
		
		String generatedString = projectService.generateParticipationCd(); // 랜덤 문자열 생성
		
		teamDTO.setTeamNm(teamNm);
		teamDTO.setMemberId(memberId);
		teamDTO.setParticipationCd(generatedString);
		
		teamDAO.insertNewTeam(teamDTO);				// 새 프로젝트 인서트

		projectService.joinProjectOrTeamMember(generatedString, memberId);			//팀 멤버 인서트
	}

	@Override
	public List<TeamDTO> getTeamList(String memberId) throws Exception {
		return teamDAO.selectListTeam(memberId);
	}

	@Override
	public boolean checkTeamMember(long teamCd, String memberId) throws Exception {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("teamCd", teamCd);
		infoMap.put("memberId", memberId);
		if (teamDAO.selectOneTeamMember(infoMap) == null) return false;
		else return true;
	}

	@Override
	public TeamDTO getTeamDTO(long teamCd) throws Exception {
		return teamDAO.selectOneTeamDTOByTeamCd(teamCd);
	}

	@Override
	public List<TeamMemberWithProfileDTO> getTeamMemberList(long teamCd) throws Exception {
		return teamDAO.selectListTeamMember(teamCd);
	}

	@Override
	public void modifyTeamAnnouncement(TeamDTO teamDTO) throws Exception {
		teamDAO.updateTeamAnnouncement(teamDTO);
	}

	@Override
	public void modifyTeamTarget(TeamDTO teamDTO) throws Exception {
		teamDAO.updateTeamTarget(teamDTO);
	}

	@Override
	public boolean leaveTeam(long teamCd, String memberId) throws Exception {
		
		boolean canLeave = false;
		
		TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
		
		teamMemberDTO.setTeamCd(teamCd);
		teamMemberDTO.setMemberId(memberId);
		
		if (teamDAO.selectOneTeamDTOByTeamCd(teamCd).getMemberId().equals(memberId)) {
			canLeave = false;
		} else {
			teamDAO.deleteTeamMember(teamMemberDTO);
			canLeave = true;
		}
		
		
		return canLeave;
		
	}

	@Override
	public List<TeamLinkDTO> getTeamLinkList(long teamCd) throws Exception {
		return teamDAO.selectListTeamLink(teamCd);
	}

}
