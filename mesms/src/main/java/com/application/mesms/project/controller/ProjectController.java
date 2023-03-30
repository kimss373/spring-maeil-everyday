package com.application.mesms.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.application.mesms.member.service.MemberService;
import com.application.mesms.project.dto.ProjectDTO;
import com.application.mesms.project.dto.ProjectMemberDTO;
import com.application.mesms.project.dto.ProjectSprintDTO;
import com.application.mesms.project.dto.ProjectWorkDTO;
import com.application.mesms.project.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/createProject")
	public String createProject() {
		return "/project/createProject";
	}
	
	@PostMapping("/createProject")
	public ResponseEntity<Object> createProject(@RequestParam String projectNm, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		projectService.createProject(projectNm, (String)session.getAttribute("memberId"));
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
			   jsScript += "location.href='" + request.getContextPath() + "/project/projectList';";
			   jsScript += " </script>";
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/projectList")
	public ModelAndView projectList(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/project/projectList");
		
		List<ProjectDTO> projectList = projectService.getProjectList(memberId);
		
		mv.addObject("projectList", projectList);
		
		System.out.println(projectList);
		
		return mv;
	}
	
	@GetMapping("/projectMain")
	public ModelAndView projectMain(@RequestParam("projectCd") long projectCd, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!projectService.checkProjectMember(projectCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		Map<String, Object> boardMap = projectService.getProjectBoardMap(projectCd);
		List<ProjectMemberDTO> projectMemberList = projectService.getProjectMemberList(projectCd);
		List<ProjectSprintDTO> projectSprintList = projectService.getProjectSprintList(projectCd);
		List<ProjectWorkDTO> projectWorkList = projectService.getProjectWorkList(projectCd);
		ProjectDTO projectDTO = projectService.getProjectDTO(projectCd);
		
		mv.setViewName("/project/projectMain");
		
		mv.addObject("boardMap", boardMap);
		mv.addObject("projectMemberList", projectMemberList);
		mv.addObject("projectSprintList", projectSprintList);
		mv.addObject("projectWorkList", projectWorkList);
		mv.addObject("projectDTO", projectDTO);
		mv.addObject("initial", projectDTO.getProjectNm().charAt(0));
		
		return mv;
		
	}
	
	@GetMapping("/projectBacklog")
	public ModelAndView projectBacklog(@RequestParam("projectCd") long projectCd, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!projectService.checkProjectMember(projectCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		List<ProjectMemberDTO> projectMemberList = projectService.getProjectMemberList(projectCd);
		List<ProjectSprintDTO> projectSprintList = projectService.getProjectSprintList(projectCd);
		List<ProjectWorkDTO> projectWorkList = projectService.getProjectWorkList(projectCd);
		ProjectDTO projectDTO = projectService.getProjectDTO(projectCd);
		
		mv.setViewName("/project/projectBacklog");
		
		mv.addObject("projectMemberList", projectMemberList);
		mv.addObject("projectSprintList", projectSprintList);
		mv.addObject("projectWorkList", projectWorkList);
		mv.addObject("projectDTO", projectDTO);
		mv.addObject("initial", projectDTO.getProjectNm().charAt(0));
		
		return mv;
	}
	
	@GetMapping("/joinProjectOrTeam")
	public String joinProjectOrTeam() {
		return "/project/joinProjectOrTeam";
	}
	
	@PostMapping("/joinProjectOrTeam")
	public ResponseEntity<Object> joinProjectOrTeam(@RequestParam String participationCd, HttpServletRequest request) throws Exception{
		//TODO
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		if (projectService.joinProjectMember(participationCd, memberId)) {
			   jsScript += "alert('가입 성공!');";
			   jsScript += "location.href='" + request.getContextPath() + "/project/projectList';";
			   jsScript += "</script>";
		} 
		else {
			   jsScript += "alert('이미 가입했거나 없는 초대코드입니다.');";
			   jsScript += "history.go(-1);";
			   jsScript += "</script>";
		}
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/createBacklog")
	public ModelAndView createBacklog(@RequestParam("projectCd") long projectCd, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!projectService.checkProjectMember(projectCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		List<ProjectMemberDTO> projectMemberList = projectService.getProjectMemberList(projectCd);
		mv.setViewName("/project/createBacklog");
		mv.addObject("projectCd", projectCd);
		mv.addObject("projectMemberList", projectMemberList);
		
		return mv;
	}
	
	@PostMapping("/createBacklog")
	public String createBacklog(@ModelAttribute ProjectWorkDTO projectWorkDTO) throws Exception {
		
		projectService.createBacklog(projectWorkDTO);
		
		return "autoClose";
	}
	
	@GetMapping("/createSprint")
	@ResponseBody
	public String createSprint(@RequestParam("projectCd") long projectCd, HttpServletRequest request) throws Exception{
		
		String jsScript = "<script>";
			   jsScript += "location.href='" + request.getContextPath() + "/project/projectBacklog?projectCd=" + projectCd + "';";
			   jsScript += "</script>";
		
		projectService.createSprint(projectCd);
		
		return jsScript;
	}
	
	@GetMapping("/inviteMember")
	public ModelAndView inviteMember(@RequestParam("projectCd") long projectCd, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!projectService.checkProjectMember(projectCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		mv.setViewName("/project/inviteMember");
		ProjectDTO projectDTO = projectService.getProjectDTO(projectCd);
		mv.addObject("projectDTO", projectDTO);

		return mv;
	}
	
	@GetMapping("/modifyBacklog")
	public ModelAndView modifyBacklog(@RequestParam("id") long id, @RequestParam("projectCd") Long projectCd, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!projectService.checkProjectMember(projectCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		List<ProjectMemberDTO> projectMemberList = projectService.getProjectMemberList(projectCd);
		List<ProjectSprintDTO> projectSprintList = projectService.getProjectSprintList(projectCd);
		ProjectWorkDTO projectWorkDTO = projectService.getProjectWorkDTOById(id);
		mv.setViewName("/project/modifyBacklog");
		mv.addObject("projectCd", projectCd);
		mv.addObject("projectMemberList", projectMemberList);		
		mv.addObject("projectSprintList", projectSprintList);		
		mv.addObject("projectWorkDTO", projectWorkDTO);
		
		return mv;
		
	}
	
	@PostMapping("/modifyBacklog")
	public String modifyBacklog(@ModelAttribute ProjectWorkDTO projectWorkDTO) throws Exception {
		
		projectService.modifyBacklog(projectWorkDTO);
		
		return "autoClose";
	}
	
	@GetMapping("/modifySprint")
	public ModelAndView modifySprint(@RequestParam("id") long id, @RequestParam("projectCd") Long projectCd, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!projectService.checkProjectMember(projectCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		ProjectSprintDTO projectSprintDTO = projectService.getProjectSprintDTOById(id);
		mv.setViewName("/project/modifySprint");
		mv.addObject("projectCd", projectCd);
		mv.addObject("projectSprintDTO", projectSprintDTO);
		
		return mv;
		
	}
	
	@PostMapping("/modifySprint")
	public String modifySprint(@ModelAttribute ProjectSprintDTO projectSprintDTO) throws Exception {
		
		projectService.modifySprint(projectSprintDTO);
		
		return "autoClose";
	}
	
	@PostMapping("/changeTodoCondition")
	@ResponseBody
	public String changeTodoCondition(@RequestParam("id") long id, @RequestParam("todoCondition") String todoCondition, @RequestParam("projectCd") long projectCd, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!projectService.checkProjectMember(projectCd, memberId)) {
			return "fail";
		}
		
		projectService.changeTodoCondition(id, todoCondition);
		
		return "success";
	}

}
