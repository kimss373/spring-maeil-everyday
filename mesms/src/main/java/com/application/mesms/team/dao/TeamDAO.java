package com.application.mesms.team.dao;

import java.util.List;
import java.util.Map;

import com.application.mesms.team.dto.TeamBoardDTO;
import com.application.mesms.team.dto.TeamDTO;
import com.application.mesms.team.dto.TeamLinkDTO;
import com.application.mesms.team.dto.TeamMemberDTO;
import com.application.mesms.team.dto.TeamMemberWithProfileDTO;

public interface TeamDAO {

	public void insertNewTeam(TeamDTO teamDTO) throws Exception;	
	public TeamDTO selectOneTeamDTOByParticipationCd(String participationCd) throws Exception;
	public TeamMemberDTO selectOneTeamMemberDTOByMemberId(Map<String, Object> infoMap) throws Exception;
	public void insertTeamMember(Map<String, Object> infoMap) throws Exception;
	public List<TeamDTO> selectListTeam(String memberId) throws Exception;
	public TeamMemberDTO selectOneTeamMember(Map<String, Object> infoMap) throws Exception;
	public TeamDTO selectOneTeamDTOByTeamCd(long teamCd) throws Exception;
	public List<TeamMemberWithProfileDTO> selectListTeamMember(long teamCd) throws Exception;
	public void updateTeamAnnouncement(TeamDTO teamDTO) throws Exception;
	public void updateTeamTarget(TeamDTO teamDTO) throws Exception;
	public void deleteTeamMember(TeamMemberDTO teamMemberDTO) throws Exception;
	public List<TeamLinkDTO> selectListTeamLink(long teamCd) throws Exception;
	public void insertNewTeamLink(TeamLinkDTO teamLinkDTO) throws Exception;
	public List<TeamBoardDTO> selectListTeamBoard(long teamCd) throws Exception;
	public void insertNewTeamBoard(TeamBoardDTO teamBoardDTO) throws Exception;
	public void updateTeamBoardReadCnt(long id) throws Exception;
	public TeamBoardDTO selectOneTeamBoardDTOById(long id) throws Exception;
	public TeamBoardDTO selectOneTeamBoardDTOIsWriter(TeamBoardDTO teamBoardDTO) throws Exception;
	public void updateTeamBoard(TeamBoardDTO teamBoardDTO) throws Exception;
	public void deleteTeamBoard(long id) throws Exception;
	
}
