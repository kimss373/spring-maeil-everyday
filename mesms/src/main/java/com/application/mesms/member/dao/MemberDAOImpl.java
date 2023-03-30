package com.application.mesms.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.mesms.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertMember(MemberDTO memberDTO) throws Exception {
		sqlSession.insert("member.insertMember", memberDTO);
	}

	@Override
	public MemberDTO selectOneDuplicatedId(String memberId) throws Exception {
		return sqlSession.selectOne("member.selectOneDuplicatedId", memberId);
	}

	@Override
	public MemberDTO selectOneLogin(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne("member.selectOneLogin", memberDTO);
	}

	@Override
	public String selectOneMemberNm(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne("member.selectOneMemberNm", memberDTO);
	}

	@Override
	public MemberDTO selectOneMemberDTO(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne("member.selectOneMemberDTO", memberDTO);
	}

	@Override
	public void updatePasswd(MemberDTO memberDTO) throws Exception {
		sqlSession.update("member.updatePasswd", memberDTO);
	}

	@Override
	public MemberDTO selectOneMemberDTOByMemberId(String memberId) throws Exception {
		return sqlSession.selectOne("member.selectOneMemberDTOByMemberId", memberId);
	}

	@Override
	public void updateProfileImage(MemberDTO memberDTO) throws Exception {
		sqlSession.update("member.updateProfileImage", memberDTO);
	}

	@Override
	public void updateMyInfo(MemberDTO memberDTO) throws Exception {
		sqlSession.update("member.updateMyInfo", memberDTO);
	}

}
