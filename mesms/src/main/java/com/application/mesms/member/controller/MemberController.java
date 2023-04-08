package com.application.mesms.member.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.application.mesms.member.dto.MemberDTO;
import com.application.mesms.member.service.MemberService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	private final String PROFILE_IMAGE_REPO_PATH = "D:\\file_repo\\";					    // window
	//private final String PROFILE_IMAGE_REPO_PATH = "/var/lib/tomcat9/file_repository/";  	// linux
	
	@GetMapping("/notYet")
	public String notYet() {
		return "/notYet";
	}
	
	@GetMapping("/registerMember")
	public String register() {		
		return "non-tiles/registerMember";
	}
	
	
	@GetMapping("checkDuplicatedId")
	public ResponseEntity<String> checkDuplicatedId(@RequestParam("memberId") String memberId) throws Exception{
		return new ResponseEntity<String>(memberService.checkDuplicatedId(memberId), HttpStatus.OK);
	}
	
	@PostMapping("/postRegister")
	public ResponseEntity<Object> register(@ModelAttribute MemberDTO memberDTO , HttpServletRequest request) throws Exception {
		
		memberService.registerMember(memberDTO);

		String message  = "<script>";
			   message += " alert('회원가입되었습니다.');";
			   message += " location.href='" + request.getContextPath() + "/';";
			   message += " </script>";
	    
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) throws Exception {
		
		String message = "";
		MemberDTO selectMemberDTO = memberService.login(memberDTO);
		if (selectMemberDTO != null) { 	
			
			HttpSession session = request.getSession();		
			session.setAttribute("memberId"   , selectMemberDTO.getMemberId());
			session.setAttribute("memberNm"   , selectMemberDTO.getMemberNm());
			session.setAttribute("profileImage"   , selectMemberDTO.getProfileImage());
			session.setMaxInactiveInterval(60 * 30);
			
			message  = "<script>";
			message += " location.href='" + request.getContextPath() + "/main';";
			message += " </script>";
			
		}
		else { 
			
			message  = "<script>";
			message += " alert('아이디와 비밀번호를 확인하세요.');";
			message += " history.go(-1);";
			message += " </script>";
			
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/findMemberId")
	public String findMemberId() {
		return "non-tiles/findMemberId";
	}
	
	
	@PostMapping("/findMemberId")
	public ResponseEntity<Object> findMemberId(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		String message = "";
		MemberDTO searchMemberDTO = memberService.getMemberDTO(memberDTO);
		if (searchMemberDTO != null) { 	
			
			message  = "<script>";
			message += "alert('" + searchMemberDTO.getMemberId() + "');";
			message += " location.href='" + request.getContextPath() + "/';";
			message += " </script>";
			
		}
		else { 
			
			message  = "<script>";
			message += " alert('입력하신 정보로 가입된 회원이 없습니다.');";
			message += " history.go(-1);";
			message += " </script>";
			
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/findPasswd")
	public String findPasswd() {
		return "non-tiles/findPasswd";
	}
	
	@PostMapping("/checkForFindPasswd")
	public ResponseEntity<Object> checkForFindPasswd(@ModelAttribute MemberDTO memberDTO) throws Exception{
		System.out.println("dd");
		System.out.println(memberDTO);
		return new ResponseEntity<Object>(memberService.getMemberDTO(memberDTO), HttpStatus.OK);
	}
	
	@PostMapping("/findPasswd")
	public ModelAndView findPasswd(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView("non-tiles/setNewPasswd");
		mv.addObject("memberId", memberDTO.getMemberId());
			
		return mv;
		
		
	}
	
	@PostMapping("/setNewPasswd")
	public ResponseEntity<Object> setNewPasswd(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) throws Exception {
		
		String message = "";
		
		memberService.setNewPasswd(memberDTO);
		
		message  = "<script>";
		message += "alert('비밀번호 재설정 완료!');";
		message += " location.href='" + request.getContextPath() + "/';";
		message += " </script>";
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Object> logout(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate(); 
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
			   jsScript += "location.href='" + request.getContextPath() + "/';";
			   jsScript += " </script>";
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	
	}
	
	@GetMapping("/thumbnails")
	public void thumbnails(@RequestParam("profileImage") String profileImage, HttpServletResponse response) throws Exception{
		
		OutputStream out = response.getOutputStream();
		String filePath = PROFILE_IMAGE_REPO_PATH + profileImage;
		
		File image = new File(filePath);
		if(image.exists()) {
			Thumbnails.of(image).size(800,800).outputFormat("png").toOutputStream(out);
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
		
	}
	
	@GetMapping("/thumbnailsSmall")
	public void thumbnailsSmall(@RequestParam("profileImage") String profileImage, HttpServletResponse response) throws Exception{
		
		OutputStream out = response.getOutputStream();
		String filePath = PROFILE_IMAGE_REPO_PATH + profileImage;
		
		File image = new File(filePath);
		if(image.exists()) {
			Thumbnails.of(image).size(45,45).outputFormat("png").toOutputStream(out);
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
		
	}
	
	@GetMapping("/member/myInfo")
	public ModelAndView myInfo(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/member/myInfo");
		
		mv.addObject("memberDTO", memberService.getMemberDTOByMemberId(memberId));
		
		return mv;
		
	}
	
	@PostMapping("/member/myInfo/changeImage")
	public ResponseEntity<Object> changeImage(MultipartHttpServletRequest multipartRequest, HttpServletRequest request) throws Exception{
		
		multipartRequest.setCharacterEncoding("utf-8");
		
		Iterator<String> file = multipartRequest.getFileNames();
		if (file.hasNext()) {
			
			MultipartFile uploadFile = multipartRequest.getFile(file.next()); 	
			
			if(!uploadFile.getOriginalFilename().isEmpty()) {
				HttpSession session = request.getSession();
				String uploadFileName = UUID.randomUUID() + "_" + uploadFile.getOriginalFilename();
				File f = new File(PROFILE_IMAGE_REPO_PATH + uploadFileName);	
				uploadFile.transferTo(f); 
				memberService.changeProfileImage(uploadFileName, (String)session.getAttribute("memberId"));
				session.setAttribute("profileImage", uploadFileName);
			}
		
		}
		
		String jsScript = "<script>";
			   jsScript += "location.href='" + request.getContextPath() + "/member/myInfo';";
			   jsScript += "</script>";			   
		
		
		return new ResponseEntity<Object>(jsScript, new HttpHeaders(), HttpStatus.OK);
		
	}
	
	@PostMapping("/member/myInfo")
	public ResponseEntity<Object> changeMyInfo(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		memberService.changeMyInfo(memberDTO);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String jsScript = "<script>";
			   jsScript += "alert('수정완료!');";
			   jsScript += "location.href='" + request.getContextPath() + "/main';";
			   jsScript += " </script>";
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	
	}
	
	@GetMapping("/setNewPasswd")
	public ModelAndView setNewPasswd(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("non-tiles/setNewPasswd");
		mv.addObject("memberId", session.getAttribute("memberId"));
		
		return mv;
	}
	
	
}
