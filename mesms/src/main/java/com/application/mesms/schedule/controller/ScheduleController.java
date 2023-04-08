package com.application.mesms.schedule.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.application.mesms.schedule.dto.ScheduleDTO;
import com.application.mesms.schedule.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/mySchedule")
	public ModelAndView mySchedule(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		List<ScheduleDTO> scheduleList = scheduleService.getScheduleListByMemberId(memberId);
		
		mv.setViewName("/mySchedule");
		mv.addObject("scheduleList", scheduleList);
		return mv;
		
	}
	
	@GetMapping("/mySchedule/schedulePopup")
	public String schedulePopup() {
		
		return "/tiles/bootstrap/contents/schedule/schedulePopup";
	}
	
	@PostMapping("/mySchedule/addSchedule")
	public ResponseEntity<Object> addSchedule(@ModelAttribute ScheduleDTO scheduleDTO, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		if (memberId == null) {
			jsScript += "alert('잘못된 접근');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.UNAUTHORIZED);
			
		}
		
		scheduleDTO.setMemberId(memberId);
		if (!scheduleService.addSchedule(scheduleDTO)) {
			jsScript += "alert('시작날짜가 끝나는 날짜보다 뒤입니다.');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		} else {
			
			jsScript += "alert('일정 등록 성공!');";
			jsScript += "opener.parent.location.reload();";
			jsScript += "window.close();";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/mySchedule/scheduleDetail")
	public String scheduleDetail(@RequestParam("scheduleCd") long scheduleCd, Model model) throws Exception {
		
		model.addAttribute("scheduleDTO", scheduleService.getScheduleDTOByScheduleCd(scheduleCd));
		
		return "/tiles/bootstrap/contents/schedule/scheduleDetail";
	}
	
	
	@PostMapping("/mySchedule/modifySchedule")
	public ResponseEntity<Object> modifySchedule(@ModelAttribute ScheduleDTO scheduleDTO, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		if (memberId == null || !memberId.equals(scheduleService.getScheduleDTOByScheduleCd(scheduleDTO.getScheduleCd()).getMemberId())) {
			jsScript += "alert('잘못된 접근');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.UNAUTHORIZED);
			
		}
		
		scheduleDTO.setMemberId(memberId);
		if (!scheduleService.modifySchedule(scheduleDTO)) {
			jsScript += "alert('시작날짜가 끝나는 날짜보다 뒤입니다.');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		} else {
			
			jsScript += "alert('일정 수정 성공!');";
			jsScript += "opener.parent.location.reload();";
			jsScript += "window.close();";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/mySchedule/deleteSchedule")
	public ResponseEntity<Object> deleteSchedule(@ModelAttribute ScheduleDTO scheduleDTO, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		if (memberId == null || !memberId.equals(scheduleService.getScheduleDTOByScheduleCd(scheduleDTO.getScheduleCd()).getMemberId())) {
			jsScript += "alert('잘못된 접근');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.UNAUTHORIZED);
			
		}
		
		scheduleService.deleteSchedule(scheduleDTO);
			
		jsScript += "alert('일정 삭제 성공!');";
		jsScript += "opener.parent.location.reload();";
		jsScript += "window.close();";
		jsScript += "</script>";
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		
	}
	
}
