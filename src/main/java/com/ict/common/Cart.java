package com.ict.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.shop.model.dao.Shop_DAO;
import com.ict.shop.model.vo.Shop_VO;

@Service
public class Cart {
	@Autowired
	private Shop_DAO shop_DAO;
	
	public  void setShop_DAO(Shop_DAO shop_DAO) {
		this.shop_DAO = shop_DAO;
	}

	 private List<Shop_VO> cartlist = new ArrayList<Shop_VO>(); // 카트에 담겨질 제품 리스트 
	 private int total ;             // 카트 전체 금액
	
	
	// 카트에 해당 제품이 있는지 없는지 검사하는 메서드 
	public  Shop_VO findProduct(String idx) {
		Shop_VO svo = null;
		Iterator<Shop_VO> it = cartlist.iterator();
		while (it.hasNext()) {
			Shop_VO shop_VO = (Shop_VO) it.next();
			if(Integer.parseInt(shop_VO.getIdx()) == Integer.parseInt(idx)) {
				svo = shop_VO;
				break;
			}
		}
		return svo;
	}
	
	// 제품을 카드에 담는 메서드 
	public  void addProduct(String idx) {
		Shop_VO svo = findProduct(idx);
		if(svo != null) {
			// 카드에 있는 현재 개수를 증가 
			svo.setQuant(svo.getQuant()+1);
			// 세일일때 금액과 세일이 아닐 때 금액 
			total = total + svo.getP_saleprice();
		}else {
			Shop_VO vo = shop_DAO.getShopOneList(idx);
			vo.setQuant(1);
			total = total + vo.getP_saleprice();
			cartlist.add(vo);
		}
	}

	public List<Shop_VO> getCartlist() {
		return cartlist;
	}

	public void setCartlist(List<Shop_VO> cartlist) {
		this.cartlist = cartlist;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	// 제품 삭제 할 때
	public void deleleProduct(String idx) {
		Shop_VO svo = findProduct(idx);
		if(svo != null) {
			cartlist.remove(svo);
			total = total - svo.getTotalPrice();
		}
	}
	// 수량을 변경 할 때 
	public void quantEdit(String idx, int su) {
		Shop_VO svo = findProduct(idx);
		if(svo != null) {
			total = total - svo.getTotalPrice();
			svo.setQuant(su);
			total = total + svo.getTotalPrice();
		}
	}
	
	
}












