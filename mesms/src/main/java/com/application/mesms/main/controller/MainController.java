package com.application.mesms.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.application.mesms.mail.service.MailService;
import com.application.mesms.mail.service.MailServiceImpl;
import com.application.mesms.project.dto.PojectWorkWithProjectNmDTO;
import com.application.mesms.project.dto.ProjectWorkDTO;
import com.application.mesms.project.service.ProjectService;
import com.application.mesms.schedule.dto.ScheduleDTO;
import com.application.mesms.schedule.service.ScheduleService;

@Controller
public class MainController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MailService mailService;

	@GetMapping("/main")
	public ModelAndView main(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if (memberId == null) {
			mv.setViewName("non-tiles/home");
			return mv;
		}
		List<ScheduleDTO> todayScheduleList = scheduleService.getTodayScheduleList(memberId);
		List<ScheduleDTO> tomorrowScheduleList = scheduleService.getTomorrowScheduleList(memberId);
		List<PojectWorkWithProjectNmDTO> myProjectWorkList = projectService.getMyProjectWorkList(memberId);
		
		
		mv.addObject("todayScheduleList", todayScheduleList);
		mv.addObject("todayScheduleSize", todayScheduleList.size());
		mv.addObject("tomorrowScheduleList", tomorrowScheduleList);
		mv.addObject("tomorrowScheduleSize", tomorrowScheduleList.size());
		mv.addObject("myProjectWorkList", myProjectWorkList);
		mv.setViewName("/main");
		
		mailService.test1();
		
		return mv;
	}
	
}
