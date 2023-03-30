package com.application.mesms.member.service;

import com.application.mesms.member.dto.MemberDTO;

public interface MemberService {

	public void registerMember(MemberDTO memberDTO) throws Exception;
	public String checkDuplicatedId(String memberId) throws Exception;
	public MemberDTO login(MemberDTO memberDTO) throws Exception;
	public String getMemberNm(MemberDTO memberDTO) throws Exception;
	public MemberDTO getMemberDTO(MemberDTO memberDTO) throws Exception;
	public void setNewPasswd(MemberDTO memberDTO) throws Exception;
	public MemberDTO getMemberDTOByMemberId(String memberId) throws Exception;
	public void changeProfileImage(String uploadFileName, String memberId) throws Exception;
	public void changeMyInfo(MemberDTO memberDTO) throws Exception;
	
}
