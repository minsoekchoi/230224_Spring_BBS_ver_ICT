package com.ict.member.model.service;

import com.ict.member.model.vo.Member_VO;

public interface Member_Service {
	
	// 로그인 
	public Member_VO getMemberLogIn(Member_VO mvo); 
	
	// 회원 가입
	public int getMemberJoin(Member_VO mvo);
	
}
