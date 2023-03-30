package com.application.mesms.member.dao;

import com.application.mesms.member.dto.MemberDTO;

public interface MemberDAO {
	
	public void insertMember(MemberDTO memberDTO) throws Exception;
	public MemberDTO selectOneDuplicatedId(String memberId) throws Exception;
	public MemberDTO selectOneLogin(MemberDTO memberDTO) throws Exception;
	public String selectOneMemberNm(MemberDTO memberDTO) throws Exception;
	public MemberDTO selectOneMemberDTO(MemberDTO memberDTO) throws Exception;
	public void updatePasswd(MemberDTO memberDTO) throws Exception;
	public MemberDTO selectOneMemberDTOByMemberId(String memberId) throws Exception;
	public void updateProfileImage(MemberDTO memberDTO) throws Exception;
	public void updateMyInfo(MemberDTO memberDTO) throws Exception;
	
}
