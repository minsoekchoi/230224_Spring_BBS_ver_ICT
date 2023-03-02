package com.ict.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.member.model.vo.Member_VO;

@Repository
public class Member_DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	// 로그인 
	public Member_VO getMemberLogIn(Member_VO mvo) {
		return sqlSessionTemplate.selectOne("member.login", mvo);
	}
	// 회원가입
	public int getMemberJoin(Member_VO mvo) {
		return sqlSessionTemplate.insert("member.join", mvo);
	}
	
}
