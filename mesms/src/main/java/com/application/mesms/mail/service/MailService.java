package com.application.mesms.mail.service;

import java.util.List;

import com.application.mesms.mail.dto.KeywordDTO;
import com.application.mesms.mail.dto.MailHistoryDTO;
import com.application.mesms.mail.dto.MessageDTO;

public interface MailService {

	public void getToken(String code, String memberId) throws Exception;
	public boolean validateMember(String memberId) throws Exception;
	public MessageDTO getLastMessage(String access_token, String emailAddress) throws Exception;
	public void test1() throws Exception;
	public void test2() throws Exception;
	public String getNewAccessToken(String refresh_token);
	public KeywordDTO getKeywordDTO(String memberId) throws Exception;
	public void modifyKeyword(String keyword, int where, String memberId) throws Exception;
	public void finishSubscription(String memberId) throws Exception;
	public List<MailHistoryDTO> getMailHistoryList(String memberId) throws Exception;
	
}
