package com.ict.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.member.model.service.Member_Service;
import com.ict.member.model.vo.Member_VO;

@Controller
public class Member_Controller {
	@Autowired
	private Member_Service member_Service;

	public void setMember_Service(Member_Service member_Service) {
		this.member_Service = member_Service;
	}
	
	@RequestMapping("member_login_form.do")
	public ModelAndView getMemberLogInForm() {
		return new ModelAndView("member/loginForm");
	}
	
	@RequestMapping("member_login.do")
	public ModelAndView getMemberLogIn(Member_VO mvo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Member_VO mvo2 = member_Service.getMemberLogIn(mvo);
		if(mvo2 == null) {
			mv.setViewName("member/login_error2");
		}else {
			 session.setAttribute("login", "ok");
			 session.setAttribute("mvo_name", mvo2.getM_name());
			 session.setAttribute("mvo_id", mvo2.getM_id());
			 // 세션ID, 디바이스ID 등 상황에 따라 필요한 정보를 담는다.
			if(mvo2.getM_id().equals("admin")) {
				session.setAttribute("admin", "ok");
			}
			// prodeuct_list 에 정보를 받기 위해서  shop2_list.do 이동 
			mv.setViewName("redirect:shop2_list.do");
		}
		return mv;
	}
	
	@RequestMapping("member_join_form.do")
	public ModelAndView getMemberJoin(Member_VO mvo) {
		ModelAndView mv = new ModelAndView("member/joinForm");
		return mv;
	}
	@RequestMapping("member_join_ok.do")
	public ModelAndView getMemberJoinOK(Member_VO mvo) {
		ModelAndView mv = new ModelAndView("index");
		int result = member_Service.getMemberJoin(mvo);
		return mv;
	}
	
	@RequestMapping("member_logout.do")
	public ModelAndView getMemberLogout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:member_login_form.do");
	}
	
}








