package com.ict.shop2.model.service;

import java.util.List;

import com.ict.shop2.model.vo.Cart_VO;
import com.ict.shop2.model.vo.Shop2_VO;

public interface Shop2_Service {
	public List<Shop2_VO> getShop2List(String category) throws Exception;
	public Shop2_VO getShop2OneList(String idx) throws Exception;
	public List<Cart_VO> getCartList(String m_id) throws Exception;
	public Shop2_VO getProductOneList(String idx) throws Exception;
	public Cart_VO getCartOneList(String m_id,String p_num) throws Exception;
	public int getCartInsert(Cart_VO vo) throws Exception;
	public int getCartUpdate(Cart_VO vo)throws Exception;
	public int getCartEdit(String cart_idx, String p_su) throws Exception;
	public int getCartDelete(String cart_idx) throws Exception;
	public int getProudctInsert(Shop2_VO vo) throws Exception;
}
