package com.application.mesms.mail.thread;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.application.mesms.mail.dao.MailDAO;
import com.application.mesms.mail.dto.KeywordDTO;
import com.application.mesms.mail.dto.MailHistoryDTO;
import com.application.mesms.mail.dto.MessageDTO;
import com.application.mesms.mail.dto.MessageListDTO;
import com.application.mesms.mail.dto.TokenDTO;
import com.application.mesms.mail.dto.TokenWithMailDTO;

@Component
@Scope("prototype")
public class MailThread extends Thread{
	
	@Autowired
	private MailDAO mailDAO;
	
	private String clientId;
	
	private String clientSecret;

	List<TokenWithMailDTO> tokenWithMailList;
	
	public MailThread(List<TokenWithMailDTO> tokenWithMailList, String clientId, String clientSecret) {
		this.tokenWithMailList = tokenWithMailList;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	@Override
	public void run(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		for (TokenWithMailDTO tokenWithMailDTO : tokenWithMailList) {
			
			String messageListUrl = "https://gmail.googleapis.com/gmail/v1/users/" + tokenWithMailDTO.getEmailAddress() + "/messages?q=is:unread&maxResults=30";
			
			String access_token = getNewAccessToken(tokenWithMailDTO.getRefreshToken());
			tokenWithMailDTO.setAccessToken(access_token);
			
			try {
				mailDAO.updateAccessToken(tokenWithMailDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + access_token);
			
			HttpEntity entity = new HttpEntity(headers);
	        ResponseEntity<MessageListDTO> messageListResponse = restTemplate.exchange(messageListUrl, HttpMethod.GET, entity, MessageListDTO.class);
	        KeywordDTO keywordDTO= null;
	        try {
				keywordDTO = getKeywordDTO(tokenWithMailDTO.getMemberId());
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        for (MessageDTO messageDTO : messageListResponse.getBody().getMessages()) {
	        	
	        	if (messageDTO.getId().equals(tokenWithMailDTO.getLastMailId())) {
	        		break;
	        	}
				
	        	JSONObject xObject = restTemplate.exchange("https://gmail.googleapis.com/gmail/v1/users/" + tokenWithMailDTO.getEmailAddress() + "/messages/" + messageDTO.getId() + "?fields=payload(headers)", HttpMethod.GET, entity, JSONObject.class).getBody();
	        	LinkedHashMap<String, List<Map<String, String>>> jsonHeaders = (LinkedHashMap<String, List<Map<String, String>>>) xObject.get("payload");
	        	List<Map<String, String>> jsonArray = jsonHeaders.get("headers");
	        	
	        	for (Map<String, String> map : jsonArray) {
					
	        		if(map.get("name").equals("Subject")) {
	        			String value = map.get("value");
	        			if (!keywordDTO.getKeyword1().equals("") && value.contains(keywordDTO.getKeyword1())) {
	        				MailHistoryDTO mailHistoryDTO = new MailHistoryDTO();
	        				mailHistoryDTO.setKeyword(keywordDTO.getKeyword1());
	        				mailHistoryDTO.setMemberId(keywordDTO.getMemberId());
	        				try {
								mailDAO.insertNewHistory(mailHistoryDTO);
							} catch (Exception e) {
								e.printStackTrace();
							}
		        		} else if (!keywordDTO.getKeyword2().equals("") && value.contains(keywordDTO.getKeyword2())) {
		        			MailHistoryDTO mailHistoryDTO = new MailHistoryDTO();
	        				mailHistoryDTO.setKeyword(keywordDTO.getKeyword2());
	        				mailHistoryDTO.setMemberId(keywordDTO.getMemberId());
	        				try {
								mailDAO.insertNewHistory(mailHistoryDTO);
							} catch (Exception e) {
								e.printStackTrace();
							}
		        		} else if (!keywordDTO.getKeyword3().equals("") && value.contains(keywordDTO.getKeyword3())) {
		        			MailHistoryDTO mailHistoryDTO = new MailHistoryDTO();
	        				mailHistoryDTO.setKeyword(keywordDTO.getKeyword3());
	        				mailHistoryDTO.setMemberId(keywordDTO.getMemberId());
	        				try {
								mailDAO.insertNewHistory(mailHistoryDTO);
							} catch (Exception e) {
								e.printStackTrace();
							}
		        		}
	        		}
	        			
				}
					
			}
	        
	        TokenWithMailDTO setTokenWithMailDTO = new TokenWithMailDTO();
	        setTokenWithMailDTO.setMemberId(tokenWithMailDTO.getMemberId());
	        setTokenWithMailDTO.setLastMailId(messageListResponse.getBody().getMessages().get(0).getId());
	        
	        try {
				mailDAO.updateLastMailId(setTokenWithMailDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public String getNewAccessToken(String refresh_token) {
		
		HttpHeaders headers = new HttpHeaders();
		
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://oauth2.googleapis.com/token";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("refresh_token", refresh_token);
        map.add("grant_type", "refresh_token");
        map.add("redirect_uri", "http://localhost:8080/mailAlarmService/getToken");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        
        ResponseEntity<TokenDTO> tokenResponse = restTemplate.exchange(url, HttpMethod.POST, entity, TokenDTO.class);
        
		return tokenResponse.getBody().getAccess_token();
		
	}

	public KeywordDTO getKeywordDTO(String memberId) throws Exception {
		return mailDAO.selectOneKeywordDTO(memberId);
	}
}
