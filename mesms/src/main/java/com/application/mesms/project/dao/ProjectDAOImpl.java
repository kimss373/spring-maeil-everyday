package com.application.mesms.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.mesms.project.dto.ProjectDTO;
import com.application.mesms.project.dto.ProjectMemberDTO;
import com.application.mesms.project.dto.ProjectSprintDTO;
import com.application.mesms.project.dto.ProjectWorkDTO;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean checkDuplicateParticipationCd(String generatedString) throws Exception {
		
		Object projectDTO = sqlSession.selectOne("project.checkDuplicateParticipationCd", generatedString);
		if (projectDTO == null) return false;
		else return true;
	}

	@Override
	public void insertNewProject(ProjectDTO projectDTO) throws Exception {
		sqlSession.insert("project.insertNewProject", projectDTO);
	}

	@Override
	public ProjectDTO selectOneProjectDTOByParticipationCd(String participationCd) throws Exception {
		return sqlSession.selectOne("project.selectOneProjectDTOByParticipationCd", participationCd);
	}
	
	@Override
	public ProjectMemberDTO selectOneProjectMemberDTOByMemberId(Map<String, Object> infoMap) throws Exception {
		return sqlSession.selectOne("project.selectOneProjectMemberDTOByMemberId", infoMap);
	}

	@Override
	public void insertProjectMember(Map<String, Object> infoMap) throws Exception {
		sqlSession.insert("project.insertProjectMember", infoMap);
	}

	@Override
	public List<ProjectDTO> selectListProject(String memberId) throws Exception {
		return sqlSession.selectList("project.selectListProject", memberId);
	}

	@Override
	public ProjectMemberDTO selectOneProjectMember(Map<String,Object> infoMap) throws Exception {
		return sqlSession.selectOne("project.selectOneProjectMember", infoMap);
	}

	@Override
	public List<ProjectMemberDTO> selectListProjectMember(long projectCd) throws Exception {
		return sqlSession.selectList("project.selectListProjectMember", projectCd);
	}

	@Override
	public List<ProjectSprintDTO> selectListProjectSprint(long projectCd) throws Exception {
		return sqlSession.selectList("project.selectListProjectSprint", projectCd);
	}

	@Override
	public List<ProjectWorkDTO> selectListProjectWork(long projectCd) throws Exception {
		return sqlSession.selectList("project.selectListProjectWork", projectCd);
	}

	@Override
	public void insertBacklog(ProjectWorkDTO projectWorkDTO) throws Exception {
		sqlSession.insert("project.insertBacklog", projectWorkDTO);
	}

	@Override
	public Long selectOneLargestNumInBacklog(long projectCd) throws Exception {
		return sqlSession.selectOne("project.selectOneLargestNumInBacklog", projectCd);
	}

	@Override
	public ProjectDTO selectOneProjectDTOByProjectCd(long projectCd) throws Exception {
		return sqlSession.selectOne("project.selectOneProjectDTOByProjectCd", projectCd);
	}

	@Override
	public Long selectOneLargestNumInSprint(long projectCd) throws Exception {
		return sqlSession.selectOne("project.selectOneLargestNumInSprint", projectCd);
	}
	
	@Override
	public void insertSprint(ProjectSprintDTO projectSprintDTO) throws Exception {
		sqlSession.insert("project.insertSprint", projectSprintDTO);
	}

	@Override
	public ProjectWorkDTO selectOneProjectWorkDTOById(long id) throws Exception {
		return sqlSession.selectOne("project.selectOneProjectWorkDTOById", id);
	}

	@Override
	public void updateBacklog(ProjectWorkDTO projectWorkDTO) throws Exception {
		sqlSession.update("project.updateBacklog", projectWorkDTO);
	}

	@Override
	public ProjectSprintDTO selectOneProjectSprintDTOById(long id) throws Exception {
		return sqlSession.selectOne("project.selectOneProjectSprintDTOById", id);
	}

	@Override
	public void updateSprint(ProjectSprintDTO projectSprintDTO) throws Exception {
		sqlSession.update("project.updateSprint", projectSprintDTO);
	}

	@Override
	public List<ProjectWorkDTO> selectListWorkinSprintList(long projectCd) throws Exception {
		return sqlSession.selectList("project.selectListWorkinSprintList", projectCd);
	}

	@Override
	public void updateTodoCondition(ProjectWorkDTO projectWorkDTO) throws Exception {
		sqlSession.update("project.updateTodoCondition", projectWorkDTO);
	}

	
}
