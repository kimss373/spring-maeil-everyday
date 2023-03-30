package com.application.mesms.member.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {

	private String memberId;
	private String memberNm;
	private String passwd;
	private String sex;
	private String dateBirth;
	private String hp;
	private String email;
	private Date joinDt;
	private String meSubscriptionYn;
	private String profileImage;

	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", memberNm=" + memberNm + ", passwd=" + passwd + ", sex=" + sex
				+ ", dateBirth=" + dateBirth + ", hp=" + hp + ", email=" + email + ", joinDt=" + joinDt
				+ ", meSubscriptionYn=" + meSubscriptionYn + ", profileImage=" + profileImage + "]";
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDt() {
		return joinDt;
	}
	public void setJoinDt(Date joinDt) {
		this.joinDt = joinDt;
	}
	public String getMeSubscriptionYn() {
		return meSubscriptionYn;
	}
	public void setMeSubscriptionYn(String meSubscriptionYn) {
		this.meSubscriptionYn = meSubscriptionYn;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
}
