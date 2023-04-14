package com.application.mesms.mail.dao;

import java.util.List;
import java.util.Map;

import com.application.mesms.mail.dto.KeywordDTO;
import com.application.mesms.mail.dto.MailHistoryDTO;
import com.application.mesms.mail.dto.TokenWithMailDTO;

public interface MailDAO {

	public void insertNewSubscription(TokenWithMailDTO tokenWithMailDTO) throws Exception;
	public List<TokenWithMailDTO> selectListAll() throws Exception;
	public void updateAccessToken(TokenWithMailDTO tokenWithMailDTO) throws Exception;
	public void insertNewKeyword(KeywordDTO keywordDTO) throws Exception;
	public KeywordDTO selectOneKeywordDTO(String memberId) throws Exception;
	public void updateKeyword(Map<String, Object> setMap) throws Exception;
	public void deleteKeyword(String memberId) throws Exception;
	public void deleteSubscription(String memberId) throws Exception;
	public TokenWithMailDTO selectOneTokenWithMail(String memberId) throws Exception;
	public void insertNewHistory(MailHistoryDTO mailHistoryDTO) throws Exception;
	public void updateLastMailId(TokenWithMailDTO setTokenWithMailDTO) throws Exception;
	public List<MailHistoryDTO> selectListMailHistory(String memberId) throws Exception;
	
}
