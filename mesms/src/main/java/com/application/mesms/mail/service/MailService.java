package com.application.mesms.mail.service;

import com.application.mesms.mail.dto.KeywordDTO;
import com.application.mesms.mail.dto.MessageDTO;

public interface MailService {

	public void getToken(String code, String memberId) throws Exception;
	public boolean validateMember(String memberId) throws Exception;
	public MessageDTO getLastMessage(String access_token, String emailAddress) throws Exception;
	public void test1() throws Exception;
	public KeywordDTO getKeywordDTO(String memberId) throws Exception;
	public void modifyKeyword(String keyword, int where, String memberId) throws Exception;
	public void finishSubscription(String memberId) throws Exception;
	
}
