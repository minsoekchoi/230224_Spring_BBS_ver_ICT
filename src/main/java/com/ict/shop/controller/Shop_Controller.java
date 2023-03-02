package com.ict.shop.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.Cart;
import com.ict.shop.model.service.Shop_Service;
import com.ict.shop.model.vo.Shop_VO;

@Controller
public class Shop_Controller {
	@Autowired
	private Shop_Service shop_Service;
	
	@Autowired
	private Cart cart;
	
	public void setShop_Service(Shop_Service shop_Service) {
		this.shop_Service = shop_Service;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	// 관리자 페이지에서 처리
	// @Autowired
	//private FileReName fileReName;
	 

	@RequestMapping("shop_list.do")
	public ModelAndView getShopList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/product_list");
		String category = request.getParameter("category");
		if(category == null || category == "") {
			category = "ele002" ;
		}
		try {
			List<Shop_VO> shoplist = shop_Service.getShopList(category);
			mv.addObject("shoplist", shoplist);
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop/error");
		}
		return mv;
	}
	@RequestMapping("shop_onelist.do")
	public ModelAndView getShopOneList(@RequestParam("idx") String idx) {
		ModelAndView mv = new ModelAndView("shop/product_content");
		try {
			Shop_VO svo = shop_Service.getShopOneList(idx);
			mv.addObject("svo", svo);
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop/error");
		}
		return mv;
	}
	
	@RequestMapping("shop_addcart.do")
	public ModelAndView getAddCart(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("redirect:shop_onelist.do");
		try {
			// 카트에 추가 
			cart.addProduct(idx);
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop/error");
		}
		return mv;
	}
	@RequestMapping("shop_showcart.do")
	public ModelAndView getShowCart() {
		ModelAndView mv = new ModelAndView("shop/cartList");
		List<Shop_VO> clist = cart.getCartlist();
		int total = cart.getTotal();
		mv.addObject("clist", clist);
		mv.addObject("total", total);
		return mv;
	}
	
	@RequestMapping("shop_cartedit.do")
	public ModelAndView getCartEdit(@RequestParam("idx")String idx, @RequestParam("su") String su) {
		ModelAndView mv = new ModelAndView("redirect:shop_showcart.do");
		 cart.quantEdit(idx, Integer.parseInt(su));
		return mv;
	}
	
	@RequestMapping("shop_cartdelete.do")
	public ModelAndView getCartDelete(@RequestParam("idx")String idx) {
		ModelAndView mv = new ModelAndView("redirect:shop_showcart.do");
		cart.deleleProduct(idx);
		return mv;
	}
}






