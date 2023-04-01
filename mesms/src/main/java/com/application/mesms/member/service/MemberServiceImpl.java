package com.application.mesms.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.mesms.member.dao.MemberDAO;
import com.application.mesms.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public String checkDuplicatedId(String memberId) throws Exception {
		
		if (memberDAO.selectOneDuplicatedId(memberId) == null) return "duplicate";
		else												   return "notDuplicate";
		
	}
	
	@Override
	public void registerMember(MemberDTO memberDTO) throws Exception {
		
		memberDTO.setPasswd(bCryptPasswordEncoder.encode(memberDTO.getPasswd()));
		memberDAO.insertMember(memberDTO);
		
	}

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception {
		
		MemberDTO selectMemberDTO = memberDAO.selectOneLogin(memberDTO);
		
		if (selectMemberDTO != null) {
			if (bCryptPasswordEncoder.matches(memberDTO.getPasswd(), selectMemberDTO.getPasswd())) {
				return selectMemberDTO;
			}
		}
		
		return null;
		
	}

	@Override
	public String getMemberNm(MemberDTO memberDTO) throws Exception {
		return memberDAO.selectOneMemberNm(memberDTO);
	}

	@Override
	public MemberDTO getMemberDTO(MemberDTO memberDTO) throws Exception {
		return memberDAO.selectOneMemberDTO(memberDTO);
	}

	@Override
	public void setNewPasswd(MemberDTO memberDTO) throws Exception {
		memberDTO.setPasswd(bCryptPasswordEncoder.encode(memberDTO.getPasswd()));
		memberDAO.updatePasswd(memberDTO);
	}

	@Override
	public MemberDTO getMemberDTOByMemberId(String memberId) throws Exception {
		return memberDAO.selectOneMemberDTOByMemberId(memberId);
	}

	@Override
	public void changeProfileImage(String uploadFileName, String memberId) throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setProfileImage(uploadFileName);
		memberDTO.setMemberId(memberId);
		memberDAO.updateProfileImage(memberDTO);
	}

	@Override
	public void changeMyInfo(MemberDTO memberDTO) throws Exception {
		memberDAO.updateMyInfo(memberDTO);		
	}

	@Override
	public boolean checkValidity(String memberId) throws Exception {
		boolean validity = false;
		
		if (memberDAO.selectOneDuplicatedId(memberId) != null) {
			validity = true;
		}
		
		return validity;
	}
	
	
	
	
	
}
