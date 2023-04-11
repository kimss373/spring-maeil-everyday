package com.application.mesms.mail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.application.mesms.mail.dao.MailDAO;
import com.application.mesms.mail.dto.KeywordDTO;
import com.application.mesms.mail.dto.MailDTO;
import com.application.mesms.mail.dto.MessageDTO;
import com.application.mesms.mail.dto.MessageListDTO;
import com.application.mesms.mail.dto.TokenDTO;
import com.application.mesms.mail.dto.TokenWithMailDTO;
import com.application.mesms.member.dao.MemberDAO;

@Service
public class MailServiceImpl implements MailService {

	@Value("#{googleConfig['GOOGLE_API_CLIENT_ID']}")
	private String clientId;
	
	@Value("#{googleConfig['GOOGLE_API_CLIENT_SECRET']}")
	private String clientSecret;
	
	@Autowired
	private MailDAO mailDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public boolean validateMember(String memberId) throws Exception {
		
		if (memberDAO.selectOneMemberDTOByMemberId(memberId).getMeSubscriptionYn().equals("N")) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void getToken(String code, String memberId) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://oauth2.googleapis.com/token";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("code", code);
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", "http://localhost:8080/mailAlarmService/getToken");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<TokenDTO> tokenResponse = restTemplate.exchange(url, HttpMethod.POST, entity, TokenDTO.class);
        TokenDTO tokenDTO = tokenResponse.getBody();
        
        
        if (tokenResponse.getStatusCode() == HttpStatus.OK) {
        	
        	restTemplate = new RestTemplate();
        	headers = new HttpHeaders();
        	headers.add("Authorization", "Bearer " + tokenResponse.getBody().getAccess_token());
        	HttpEntity mailEntity = new HttpEntity(headers);
        	String urlGetMailInfo = "https://gmail.googleapis.com/gmail/v1/users/me/profile";
        	
        	ResponseEntity<MailDTO> mailResponse = restTemplate.exchange(urlGetMailInfo, HttpMethod.GET, mailEntity, MailDTO.class);
        	MailDTO mailDTO = mailResponse.getBody();
        	
        	memberDAO.updateMeSubscriptionY(memberId);
        	MessageDTO messageDTO = getLastMessage(tokenDTO.getAccess_token(), mailDTO.getEmailAddress());
        	
        	TokenWithMailDTO tokenWithMailDTO = new TokenWithMailDTO();
        	tokenWithMailDTO.setMemberId(memberId);
        	tokenWithMailDTO.setAccessToken(tokenDTO.getAccess_token());
        	tokenWithMailDTO.setRefreshToken(tokenDTO.getRefresh_token());
        	tokenWithMailDTO.setEmailAddress(mailDTO.getEmailAddress());
        	tokenWithMailDTO.setLastMailId(messageDTO.getId());
        	
        	KeywordDTO keywordDTO = new KeywordDTO();
        	keywordDTO.setMemberId(memberId);
        	keywordDTO.setEmailAddress(mailDTO.getEmailAddress());
        	
        	mailDAO.insertNewSubscription(tokenWithMailDTO);
        	mailDAO.insertNewKeyword(keywordDTO);
        	
        }
        
		
	}
	
	@Override
	public MessageDTO getLastMessage(String access_token, String emailAddress) throws Exception {
		
		String url = "https://gmail.googleapis.com/gmail/v1/users/" + emailAddress + "/messages?maxResults=1";
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + access_token);
        HttpEntity entity = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MessageListDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, MessageListDTO.class);
        
        return response.getBody().getMessages().get(0);
		
	}
	
	
	@Override
	public void test1() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<TokenWithMailDTO> tokenWithMailList = mailDAO.selectListAll();
		
		for (TokenWithMailDTO tokenWithMailDTO : tokenWithMailList) {
			
			String messageListUrl = "https://gmail.googleapis.com/gmail/v1/users/" + tokenWithMailDTO.getEmailAddress() + "/messages?maxResults=30";
			
			String access_token = getNewAccessToken(tokenWithMailDTO.getRefreshToken());
			tokenWithMailDTO.setAccessToken(access_token);
			
			mailDAO.updateAccessToken(tokenWithMailDTO);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + access_token);
			
			HttpEntity entity = new HttpEntity(headers);
	        ResponseEntity<MessageListDTO> messageListResponse = restTemplate.exchange(messageListUrl, HttpMethod.GET, entity, MessageListDTO.class);
	        
	        for (MessageDTO messageDTO : messageListResponse.getBody().getMessages()) {
	        	
	        	if (messageDTO.getId().equals(tokenWithMailDTO.getLastMailId())) {
	        		break;
	        	}
				
	        	ResponseEntity<String> xString = restTemplate.exchange("https://gmail.googleapis.com/gmail/v1/users/" + tokenWithMailDTO.getEmailAddress() + "/messages/" + messageDTO.getId(), HttpMethod.GET, entity, String.class);
	        	JSONParser parser = new JSONParser();
		        JSONObject jsonObject = (JSONObject) parser.parse(xString.getBody());
		        JSONObject jsonHeaders = (JSONObject) parser.parse(jsonObject.get("payload").toString());
		        JSONArray jArray = (JSONArray) jsonHeaders.get("headers");
		        
		        for (int i = 0 ; i < jArray.size() ; i++) {
		        	JSONObject tmp = (JSONObject) parser.parse(jArray.get(i).toString());
		        	if (tmp.get("name").equals("Subject")) {
		        		System.out.println(i);
		        		System.out.println(tmp.get("value"));
		        		System.out.println("ㅋㅋ".toUpperCase());
		        	}
		        }
			}
	        
	        // mailDAO.updateLastMailId(messageListResponse.getBody().getMessages().get(0).getId());
			
		}
		
	}
	
	private String getNewAccessToken(String refresh_token) {
		
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

	@Override
	public KeywordDTO getKeywordDTO(String memberId) throws Exception {
		return mailDAO.selectOneKeywordDTO(memberId);
	}

	@Override
	public void modifyKeyword(String keyword, int where, String memberId) throws Exception {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("keyword", keyword);
		setMap.put("where", where);
		setMap.put("memberId", memberId);
		
		mailDAO.updateKeyword(setMap);
		
	}

	@Override
	public void finishSubscription(String memberId) throws Exception {

		TokenWithMailDTO tokenWithMailDTO = mailDAO.selectOneTokenWithMail(memberId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-Type", "application/x-www-form-urlencoded");
		
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://oauth2.googleapis.com/revoke?token=" + tokenWithMailDTO.getRefreshToken();
        
        HttpEntity entity = new HttpEntity(headers);
        
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        
        System.out.println("성공");
		
		memberDAO.updateMeSubscriptionN(memberId);
		mailDAO.deleteKeyword(memberId);
		mailDAO.deleteSubscription(memberId);
		
	}
	
}
