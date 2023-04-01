package com.application.mesms.team.service;

import java.util.List;

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

}
