package com.application.mesms.team.controller;

import java.util.List;

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

import com.application.mesms.member.dto.MemberDTO;
import com.application.mesms.member.service.MemberService;
import com.application.mesms.project.dto.ProjectDTO;
import com.application.mesms.project.dto.ProjectSprintDTO;
import com.application.mesms.team.dto.TeamBoardDTO;
import com.application.mesms.team.dto.TeamDTO;
import com.application.mesms.team.dto.TeamLinkDTO;
import com.application.mesms.team.dto.TeamMemberDTO;
import com.application.mesms.team.dto.TeamMemberWithProfileDTO;
import com.application.mesms.team.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TeamService teamService;
	
	@GetMapping("/createTeam")
	public String createTeam() {
		return "/team/createTeam";
	}
	
	@PostMapping("/createTeam")
	public ResponseEntity<Object> createTeam(@RequestParam String teamNm, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		String memberId = (String)session.getAttribute("memberId");
		if (!memberService.checkValidity(memberId)) {
			jsScript += "location.href='" + request.getContextPath() + "/main';";
			jsScript += " </script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.FORBIDDEN);
		}
		
		teamService.createTeam(teamNm, memberId);
		
	    jsScript += "location.href='" + request.getContextPath() + "/team/teamList';";
	    jsScript += " </script>";
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/teamList")
	public ModelAndView teamList(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/team/teamList");
		
		List<TeamDTO> teamList = teamService.getTeamList(memberId);
		
		mv.addObject("teamList", teamList);
		
		return mv;
	}
	
	@GetMapping("/teamMain")
	public ModelAndView teamMain(@RequestParam("teamCd") long teamCd, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		TeamDTO teamDTO = teamService.getTeamDTO(teamCd);
		List<TeamMemberWithProfileDTO> teamMemberList = teamService.getTeamMemberList(teamCd);
		List<TeamLinkDTO> teamLinkList = teamService.getTeamLinkList(teamCd);
		
		mv.addObject("teamDTO", teamDTO);
		mv.addObject("teamMemberList", teamMemberList);
		mv.addObject("teamLinkList", teamLinkList);
		
		mv.setViewName("/team/teamMain");
		return mv;
	}
	
	@GetMapping("/showParticipationCd")
	public ModelAndView modifySprint(@RequestParam("teamCd") Long teamCd, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		TeamDTO teamDTO = teamService.getTeamDTO(teamCd);
		mv.setViewName("/team/showParticipationCd");
		mv.addObject("teamDTO", teamDTO);
		
		return mv;
		
	}
	
	@GetMapping("/modifyTeamAnnouncement")
	public ModelAndView modifyTeamAnnouncement(@RequestParam("teamCd") long teamCd, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		TeamDTO teamDTO = teamService.getTeamDTO(teamCd);
		mv.setViewName("/team/modifyTeamAnnouncement");
		mv.addObject("teamDTO", teamDTO);
		
		return mv;
		
	}
	
	@PostMapping("/modifyTeamAnnouncement")
	public String modifyTeamAnnouncement(@ModelAttribute TeamDTO teamDTO, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamDTO.getTeamCd(), memberId)) {
			
			return "/main";
		}
		
		teamService.modifyTeamAnnouncement(teamDTO);
		
		return "autoClose";
	}
	
	@GetMapping("/modifyTeamTarget")
	public ModelAndView modifyTeamTarget(@RequestParam("teamCd") long teamCd, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		TeamDTO teamDTO = teamService.getTeamDTO(teamCd);
		mv.setViewName("/team/modifyTeamTarget");
		mv.addObject("teamDTO", teamDTO);
		
		return mv;
		
	}
	
	@PostMapping("/modifyTeamTarget")
	public String modifyTeamTarget(@ModelAttribute TeamDTO teamDTO, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamDTO.getTeamCd(), memberId)) {
			
			return "/main";
		}
		
		teamService.modifyTeamTarget(teamDTO);
		
		return "autoClose";
	}
	
	@PostMapping("/leaveTeam")
	public ResponseEntity<Object> leaveTeam(@RequestParam("teamCd") long teamCd, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		if (teamService.leaveTeam(teamCd, memberId)) {
			jsScript += "alert('탈퇴 완료');";
			jsScript += "location.href='" + request.getContextPath() + "/team/teamList';";
			jsScript += "</script>";
		} else {
			jsScript += "alert('팀장은 탈퇴할 수 없습니다.');";
			jsScript += "location.href='" + request.getContextPath() + "/team/teamMain?teamCd=" + teamCd + "';";
			jsScript += "</script>";
		}
		
		
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/createTeamLink")
	public ModelAndView createTeamLink(@RequestParam("teamCd") long teamCd, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
			
		mv.setViewName("/team/createTeamLink");
		mv.addObject("memberId", memberId);
		mv.addObject("teamCd", teamCd);
		return mv;
	}
	
	@PostMapping("/createTeamLink")
	public String createTeamLink(@ModelAttribute TeamLinkDTO teamLinkDTO, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamLinkDTO.getTeamCd(), memberId)) {
			
			return "/main";
		}
		teamLinkDTO.setMemberId(memberId);
		
		teamService.createTeamLink(teamLinkDTO);
		
		return "autoClose";
	}
	
	@GetMapping("/teamBoard")
	public ModelAndView teamBoard(@RequestParam("teamCd") long teamCd, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		TeamDTO teamDTO = teamService.getTeamDTO(teamCd);
		List<TeamBoardDTO> teamBoardList = teamService.getTeamBoardList(teamCd);
		
		mv.addObject("teamDTO", teamDTO);
		mv.addObject("teamBoardList", teamBoardList);
		mv.addObject("boardCnt", teamBoardList.size());
		
		mv.setViewName("/team/teamBoard");
		return mv;
	}
	
	@GetMapping("/createTeamBoard")
	public ModelAndView createTeamBoard(@RequestParam("teamCd") long teamCd, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		if (!teamService.checkTeamMember(teamCd, memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		TeamDTO teamDTO = teamService.getTeamDTO(teamCd);
		
		mv.addObject("teamDTO", teamDTO);
		
		mv.setViewName("/team/createTeamBoard");
		return mv;
	}
	
	@PostMapping("/createTeamBoard")
	public ResponseEntity<Object> createTeamBoard(@ModelAttribute TeamBoardDTO teamBoardDTO, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		String jsScript = "<script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		if (!teamService.checkTeamMember(teamBoardDTO.getTeamCd(), memberId)) { // 팀에 가입되지 않은 회원일 경우
			jsScript += "alert('잘못된 접근');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.UNAUTHORIZED);
		}
		
		teamBoardDTO.setMemberId(memberId);
		
		int validity = teamService.createTeamBoard(teamBoardDTO);
		
		if (validity == 1) { // 제목이 조건에 맞지 않을 때
			jsScript += "alert('제목은 5글자 이상이어야 하며, 공백만으로는 만들 수 없습니다.');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.NOT_ACCEPTABLE);
		}
		else if (validity == 2) { // 본문이 조건에 맞지 않을 때
			jsScript += "alert('본문은 5글자 이상이어야 하며, 공백만으로는 만들 수 없습니다.');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.NOT_ACCEPTABLE);
		}
		else { // 게시글 등록 성공
			jsScript += "alert('게시글 등록!');";
			jsScript += "location.href='" + request.getContextPath() + "/team/teamBoard?teamCd=" + teamBoardDTO.getTeamCd() + "';";
			jsScript += "</script>";
		}
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/teamBoardDetail")
	public ModelAndView teamBoardDetail(@RequestParam("id") long id, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		TeamBoardDTO teamBoardDTO = teamService.getTeamBoardDetail(id, true);
		
		if (!teamService.checkTeamMember(teamBoardDTO.getTeamCd(), memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		mv.addObject("teamBoardDTO", teamBoardDTO);
		
		mv.setViewName("/team/teamBoardDetail");
		return mv;
	}
	
	@GetMapping("/modifyTeamBoard")
	public ModelAndView modifyTeamBoard(@RequestParam("id") long id, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		TeamBoardDTO teamBoardDTO = teamService.getTeamBoardDetail(id, false);
		
		if (!teamService.checkTeamMember(teamBoardDTO.getTeamCd(), memberId)) {
			mv.setViewName("/main");
			return mv;
		}
		
		mv.addObject("teamBoardDTO", teamBoardDTO);
		
		mv.setViewName("/team/modifyTeamBoard");
		return mv;
	}
	
	@PostMapping("/modifyTeamBoard")
	public ResponseEntity<Object> modifyTeamBoard(@ModelAttribute TeamBoardDTO teamBoardDTO, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		if (!teamService.checkTeamMember(teamBoardDTO.getTeamCd(), memberId) || !teamService.isWriter(teamBoardDTO.getId(), memberId)) { // 팀에 가입되지 않은 회원 or 자기 글이 아닐 경우
			jsScript += "alert('잘못된 접근');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.UNAUTHORIZED);
		}
		
		teamBoardDTO.setMemberId(memberId);
		
		int validity = teamService.modifyTeamBoard(teamBoardDTO);
		
		if (validity == 1) { // 제목이 조건에 맞지 않을 때
			jsScript += "alert('제목은 5글자 이상이어야 하며, 공백만으로는 만들 수 없습니다.');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.NOT_ACCEPTABLE);
		}
		else if (validity == 2) { // 본문이 조건에 맞지 않을 때
			jsScript += "alert('본문은 5글자 이상이어야 하며, 공백만으로는 만들 수 없습니다.');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.NOT_ACCEPTABLE);
		}
		else { // 게시글 수정 성공
			jsScript += "alert('게시글 수정완료!');";
			jsScript += "location.href='" + request.getContextPath() + "/team/teamBoardDetail?id=" + teamBoardDTO.getId() + "';";
			jsScript += "</script>";
		}
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	}
	
	@PostMapping("/deleteTeamBoard")
	public ResponseEntity<Object> deleteTeamBoard(@RequestParam("id") long id, @RequestParam("teamCd") long teamCd, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId =  (String)session.getAttribute("memberId");
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
		
		if (!teamService.isWriter(id, memberId)) { // 자기 글이 아닐 경우
			jsScript += "alert('잘못된 접근');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.UNAUTHORIZED);
		}
		
		teamService.deleteTeamBoard(id);
		
		jsScript += "alert('삭제 완료');";
		jsScript += "location.href='" + request.getContextPath() + "/team/teamBoard?teamCd=" + teamCd + "';";
		jsScript += "</script>";
		
		
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		
	}
}
