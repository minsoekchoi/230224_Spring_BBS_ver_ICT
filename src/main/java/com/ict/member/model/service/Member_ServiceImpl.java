package com.ict.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ict.member.model.dao.Member_DAO;
import com.ict.member.model.vo.Member_VO;

@Service
public class Member_ServiceImpl implements Member_Service {
	@Autowired
	private Member_DAO member_DAO;

	public void setMember_DAO(Member_DAO member_DAO) {
		this.member_DAO = member_DAO;
	}

	// 로그인
	@Override
	public Member_VO getMemberLogIn(Member_VO mvo) {
		return member_DAO.getMemberLogIn(mvo);
	}

	// 회원가입
	@Override
	public int getMemberJoin(Member_VO mvo) {
		return member_DAO.getMemberJoin(mvo);
	}
}
