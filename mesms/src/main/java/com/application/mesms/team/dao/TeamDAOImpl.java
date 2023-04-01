package com.application.mesms.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.mesms.team.dto.TeamDTO;
import com.application.mesms.team.dto.TeamLinkDTO;
import com.application.mesms.team.dto.TeamMemberDTO;
import com.application.mesms.team.dto.TeamMemberWithProfileDTO;

@Repository
public class TeamDAOImpl implements TeamDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertNewTeam(TeamDTO teamDTO) throws Exception {
		sqlSession.insert("team.insertNewTeam", teamDTO);
	}

	@Override
	public TeamDTO selectOneTeamDTOByParticipationCd(String participationCd) throws Exception {
		return sqlSession.selectOne("team.selectOneTeamDTOByParticipationCd", participationCd);
	}

	@Override
	public TeamMemberDTO selectOneTeamMemberDTOByMemberId(Map<String, Object> infoMap) throws Exception {
		return sqlSession.selectOne("team.selectOneTeamMemberDTOByMemberId", infoMap);
	}

	@Override
	public void insertTeamMember(Map<String, Object> infoMap) throws Exception {
		sqlSession.insert("team.insertTeamMember", infoMap);
	}

	@Override
	public List<TeamDTO> selectListTeam(String memberId) throws Exception {
		return sqlSession.selectList("team.selectListTeam", memberId);
	}

	@Override
	public TeamMemberDTO selectOneTeamMember(Map<String, Object> infoMap) throws Exception {
		return sqlSession.selectOne("team.selectOneTeamMember", infoMap);
	}

	@Override
	public TeamDTO selectOneTeamDTOByTeamCd(long teamCd) throws Exception {
		return sqlSession.selectOne("team.selectOneTeamDTOByTeamCd", teamCd);
	}

	@Override
	public List<TeamMemberWithProfileDTO> selectListTeamMember(long teamCd) throws Exception {
		return sqlSession.selectList("team.selectListTeamMember", teamCd);
	}

	@Override
	public void updateTeamAnnouncement(TeamDTO teamDTO) throws Exception {
		sqlSession.update("team.updateTeamAnnouncement", teamDTO);
	}

	@Override
	public void updateTeamTarget(TeamDTO teamDTO) throws Exception {
		sqlSession.update("team.updateTeamTarget", teamDTO);
	}

	@Override
	public void deleteTeamMember(TeamMemberDTO teamMemberDTO) throws Exception {
		sqlSession.delete("team.deleteTeamMember", teamMemberDTO);
	}

	@Override
	public List<TeamLinkDTO> selectListTeamLink(long teamCd) throws Exception {
		return sqlSession.selectList("team.selectListTeamLink", teamCd);
	}

}
