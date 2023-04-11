package com.application.mesms.mail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.application.mesms.mail.dto.KeywordDTO;
import com.application.mesms.mail.service.MailService;
import com.application.mesms.member.dto.MemberDTO;
import com.application.mesms.member.service.MemberService;

@Controller
@RequestMapping("/mailAlarmService")
public class MailController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailService mailService;
	
	@Value("#{googleConfig['GOOGLE_API_CLIENT_ID']}")
	private String clientId;
	
	@GetMapping("")
	public ModelAndView mailAlarmService(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		ModelAndView mv = new ModelAndView("");
		
		if (memberId == null) {
			mv.setViewName("/main");
			return mv;
		}
		
		MemberDTO memberDTO = memberService.getMemberDTOByMemberId(memberId);
		
		if (memberDTO.getMeSubscriptionYn().equals("N")) {
			mv.setViewName("/mailAlarmServiceJoin");
		} else if (memberDTO.getMeSubscriptionYn().equals("Y")) {
			mv.addObject("keywordDTO", mailService.getKeywordDTO(memberId));
			mv.setViewName("/mailAlarmServiceMain");
		}
			
		return mv;
		
	}
	
	@GetMapping("/join")
	public String join(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if (!mailService.validateMember(memberId)) {
			return "/main";
		}
		
		return "redirect:https://accounts.google.com/o/oauth2/v2/auth?"
                + "client_id=" + clientId + "&"
                + "redirect_uri=" + "http://localhost:8080/mailAlarmService/getToken" + "&"
                + "response_type=" + "code" + "&"
                + "scope=" + "https://mail.google.com/" + "&"
                + "access_type=" + "offline";
	}
	
	@GetMapping("/getToken")
	public String getToken(@RequestParam("code") String code, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if (!mailService.validateMember(memberId)) {
			return "/main";
		}
		
		mailService.getToken(code, memberId);
		System.out.println(code);
		
		return "redirect:/mailAlarmService";
	}
	
	@GetMapping("/modifyKeyword")
	public ModelAndView modifyKeyword(@RequestParam("keyword") String keyword, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if (mailService.validateMember(memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		KeywordDTO keywordDTO = mailService.getKeywordDTO(memberId);
		
		if (keyword.equals("keyword1")) {
			mv.addObject("where", 1);
			mv.addObject("keyword", keywordDTO.getKeyword1());
		}
		else if (keyword.equals("keyword2")) {
			mv.addObject("where", 2);
			mv.addObject("keyword", keywordDTO.getKeyword2());
		}
		else if (keyword.equals("keyword3")) {
			mv.addObject("where", 3);
			mv.addObject("keyword", keywordDTO.getKeyword3());
		}
		
		mv.setViewName("/mailAlarmService/modifyKeyword");
		
		return mv;
		
	}
	
	@PostMapping("/modifyKeyword")
	public String modifyKeyword(@RequestParam("keyword") String keyword, @RequestParam("where") int where, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if (mailService.validateMember(memberId)) {
			return "/main";
		}
		
		mailService.modifyKeyword(keyword, where, memberId);
		
		return "autoClose";
	}
	
	@PostMapping("/finishSubscription")
	public ResponseEntity<Object> finishSubscription(@RequestParam("memberId") String memberId, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String sessionMemberId =  (String)session.getAttribute("memberId");
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		if (!memberId.equals(sessionMemberId)) {
		
			jsScript += "location.href='" + request.getContextPath() + "/main';";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.UNAUTHORIZED);
		}
		
		mailService.finishSubscription(memberId);
		
		jsScript += "alert('서비스 취소 완료');";
		jsScript += "location.href='" + request.getContextPath() + "/mailAlarmService';";
		jsScript += "</script>";
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		
	}
	
}
