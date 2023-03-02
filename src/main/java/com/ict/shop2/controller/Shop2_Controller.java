package com.ict.shop2.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.FileReName;
import com.ict.shop2.model.service.Shop2_Service;
import com.ict.shop2.model.vo.Cart_VO;
import com.ict.shop2.model.vo.Shop2_VO;

@Controller
public class Shop2_Controller {
	@Autowired
	private Shop2_Service shop2_Service;
	public void setShop2_Service(Shop2_Service shop2_Service) {
		this.shop2_Service = shop2_Service;
	}
	
	// 관리자 페이지에서 처리
	@Autowired
	private FileReName fileReName;
	public void setFileReName(FileReName fileReName) {
		this.fileReName = fileReName;
	}
	
	@RequestMapping("shop2_list.do")
	public ModelAndView getShop2List(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop2/product_list");
		String category = request.getParameter("category");
		if(category == null || category == "") {
			category = "ele002" ;
		}
		try {
			List<Shop2_VO> shop2list = shop2_Service.getShop2List(category);
			mv.addObject("shop2list", shop2list);
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop2/error");
		}
		return mv;
	}
	@RequestMapping("shop2_onelist.do")
	public ModelAndView getShop2OneList(@RequestParam("idx") String idx) {
		ModelAndView mv = new ModelAndView("shop2/product_content");
		try {
			Shop2_VO s2vo = shop2_Service.getShop2OneList(idx);
			mv.addObject("s2vo", s2vo);
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop2/error");
		}
		return mv;
	}
	
	@RequestMapping("shop2_addcart.do")
	public ModelAndView getAddCart(@ModelAttribute("idx") String idx,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:shop2_onelist.do");
		String m_id = (String)session.getAttribute("mvo_id");
		
		try {
			
		// idx를 이용해서 제품정보 가져오기 
		Shop2_VO  s2vo = shop2_Service.getProductOneList(idx);
		
		// m_id와 제품 번호 가지고 카트 정보 가져오기 
		Cart_VO c2vo = shop2_Service.getCartOneList(m_id, s2vo.getP_num());
		
		
		if(c2vo == null) {
		// 해당 제품이 없으면 카트에 추가
			Cart_VO vo = new Cart_VO();
			vo.setP_num(s2vo.getP_num());
			vo.setP_name(s2vo.getP_name());
			vo.setP_price(String.valueOf(s2vo.getP_price()));
			vo.setP_saleprice(String.valueOf(s2vo.getP_saleprice()));
			vo.setM_id(m_id);
			int res = shop2_Service.getCartInsert(vo);
		}else {
		// 해당 제품이 있으며 카트에 있는 제품의 개수를 1증가
			int res = shop2_Service.getCartUpdate(c2vo);
		}
		 
		} catch (Exception e) {
			System.out.println(e);
		}
		return mv;
	}
	@RequestMapping("shop2_showcart.do")
	public ModelAndView getShowCart(HttpSession session) {
		ModelAndView mv = new ModelAndView("shop2/cartList");
		try {
			String m_id = (String)session.getAttribute("mvo_id");
			List<Cart_VO> clist =  shop2_Service.getCartList(m_id);
			
			mv.addObject("clist", clist);
			
		} catch (Exception e) {
			return null;
		}
		return mv;
	}
	
	@RequestMapping("shop2_cartedit.do")
	public ModelAndView getCartEdit(@RequestParam("cart_idx")String cart_idx, 
			@RequestParam("p_su") String p_su) {
		ModelAndView mv = new ModelAndView("redirect:shop2_showcart.do");
		try {
			int res = shop2_Service.getCartEdit(cart_idx, p_su);
		} catch (Exception e) {
		}
		return mv;
	}
	
	@RequestMapping("shop2_cartdelete.do")
	public ModelAndView getCartDelete(@RequestParam("cart_idx")String cart_idx) {
		ModelAndView mv = new ModelAndView("redirect:shop2_showcart.do");
		try {
			int res = shop2_Service.getCartDelete(cart_idx);
		} catch (Exception e) {
		}
		return mv;
	}
	
	@RequestMapping("shop2_product_ins.do")
	public ModelAndView getProductInsertForm() {
		ModelAndView mv = new ModelAndView("shop2/product_add");
		return mv;
	}
	
	@RequestMapping("shop2_product_ins_ok.do")
	public ModelAndView getProductInsOk(Shop2_VO vo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:shop2_list.do?category="+vo.getCategory());
		try {
			String path = session.getServletContext().getRealPath("/resources/images");
			MultipartFile s_image_s = vo.getS_image_s();
			MultipartFile s_image_l = vo.getS_image_l();
			
			String reName1 = fileReName.exec(path, s_image_s.getOriginalFilename());
			String reName2 = fileReName.exec(path, s_image_l.getOriginalFilename());
			vo.setP_image_s(reName1);
			vo.setP_image_l(reName2);
			
			int res = shop2_Service.getProudctInsert(vo);
			if(res>0) {
				s_image_s.transferTo(new File(path+"/"+vo.getP_image_s()));
				s_image_l.transferTo(new File(path+"/"+vo.getP_image_l()));
			}
		} catch (Exception e) {
		}
		return mv;
	}
}






