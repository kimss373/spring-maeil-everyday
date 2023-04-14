package com.application.mesms.mail.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.mesms.mail.dto.KeywordDTO;
import com.application.mesms.mail.dto.MailHistoryDTO;
import com.application.mesms.mail.dto.TokenWithMailDTO;

@Repository
public class MailDAOImpl implements MailDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertNewSubscription(TokenWithMailDTO tokenWithMailDTO) throws Exception {
		sqlSession.insert("mail.insertNewSubscription", tokenWithMailDTO);
	}

	@Override
	public List<TokenWithMailDTO> selectListAll() throws Exception {
		return sqlSession.selectList("mail.selectListAll");
	}

	@Override
	public void updateAccessToken(TokenWithMailDTO tokenWithMailDTO) throws Exception {
		sqlSession.update("mail.updateAccessToken", tokenWithMailDTO);
	}

	@Override
	public void insertNewKeyword(KeywordDTO keywordDTO) throws Exception {
		sqlSession.insert("mail.insertNewKeyword", keywordDTO);
	}

	@Override
	public KeywordDTO selectOneKeywordDTO(String memberId) throws Exception {
		return sqlSession.selectOne("mail.selectOneKeywordDTO", memberId);
	}

	@Override
	public void updateKeyword(Map<String, Object> setMap) throws Exception {
		sqlSession.update("mail.updateKeyword", setMap);
	}

	@Override
	public void deleteKeyword(String memberId) throws Exception {
		sqlSession.delete("mail.deleteKeyword", memberId);
	}

	@Override
	public void deleteSubscription(String memberId) throws Exception {
		sqlSession.delete("mail.deleteSubscription", memberId);
	}

	@Override
	public TokenWithMailDTO selectOneTokenWithMail(String memberId) throws Exception {
		return sqlSession.selectOne("mail.selectOneTokenWithMail", memberId);
	}

	@Override
	public void insertNewHistory(MailHistoryDTO mailHistoryDTO) throws Exception {
		sqlSession.insert("mail.insertNewHistory", mailHistoryDTO);
	}

	@Override
	public void updateLastMailId(TokenWithMailDTO setTokenWithMailDTO) throws Exception {
		sqlSession.update("mail.updateLastMailId", setTokenWithMailDTO);
	}

	@Override
	public List<MailHistoryDTO> selectListMailHistory(String memberId) throws Exception {
		return sqlSession.selectList("mail.selectListMailHistory", memberId);
	}
	
}
