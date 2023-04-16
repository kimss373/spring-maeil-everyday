package com.application.mesms.team.service;

import java.util.List;

import com.application.mesms.team.dto.TeamBoardDTO;
import com.application.mesms.team.dto.TeamDTO;
import com.application.mesms.team.dto.TeamLinkDTO;
import com.application.mesms.team.dto.TeamMemberDTO;
import com.application.mesms.team.dto.TeamMemberWithProfileDTO;

public interface TeamService {
	
	public void createTeam(String teamNm, String memberId) throws Exception;
	boolean joinTeamMember(String participationCd, String memberId) throws Exception;
	public List<TeamDTO> getTeamList(String memberId) throws Exception;
	public boolean checkTeamMember(long teamCd, String memberId) throws Exception;
	public TeamDTO getTeamDTO(long teamCd) throws Exception;
	public List<TeamMemberWithProfileDTO> getTeamMemberList(long teamCd) throws Exception;
	public void modifyTeamAnnouncement(TeamDTO teamDTO) throws Exception;
	public void modifyTeamTarget(TeamDTO teamDTO) throws Exception;
	public boolean leaveTeam(long teamCd, String memberId) throws Exception;
	public List<TeamLinkDTO> getTeamLinkList(long teamCd) throws Exception;
	public void createTeamLink(TeamLinkDTO teamLinkDTO) throws Exception;
	public TeamLinkDTO getTeamLinkDTO(long id) throws Exception;
	public void modifyTeamLink(TeamLinkDTO teamLinkDTO) throws Exception;
	public void deleteTeamLink(long id) throws Exception;
	public List<TeamBoardDTO> getTeamBoardList(long teamCd) throws Exception;
	public int createTeamBoard(TeamBoardDTO teamBoardDTO) throws Exception;
	public TeamBoardDTO getTeamBoardDetail(long id, boolean isReadCntUp) throws Exception;
	public boolean isWriter(long id, String memberId) throws Exception;
	public int modifyTeamBoard(TeamBoardDTO teamBoardDTO) throws Exception;
	public void deleteTeamBoard(long id) throws Exception;

}
