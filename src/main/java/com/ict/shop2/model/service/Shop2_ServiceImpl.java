package com.ict.shop2.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ict.shop2.model.dao.Shop2_DAO;
import com.ict.shop2.model.vo.Cart_VO;
import com.ict.shop2.model.vo.Shop2_VO;

@Service
public class Shop2_ServiceImpl implements Shop2_Service{
	@Autowired
	private Shop2_DAO shop2_DAO;
	public void setShop2_DAO(Shop2_DAO shop2_DAO) {
		this.shop2_DAO = shop2_DAO;
	}
	
	@Override
	public List<Shop2_VO> getShop2List(String category) throws Exception {
		return shop2_DAO.getShop2List(category);
	}
	@Override
	public Shop2_VO getShop2OneList(String idx) throws Exception {
		return shop2_DAO.getShop2OneList(idx);
	}
	
	@Override
	public List<Cart_VO> getCartList(String m_id) throws Exception {
		return shop2_DAO.getCartList(m_id);
	}

	@Override
	public Shop2_VO getProductOneList(String idx) throws Exception {
		return shop2_DAO.getProductOneList(idx);
	}
	
	@Override
	public Cart_VO getCartOneList(String m_id, String p_num) throws Exception {
		return shop2_DAO.getCartOneList(m_id, p_num);
	}
	
	@Override
	public int getCartInsert(Cart_VO vo) throws Exception {
		return shop2_DAO.getCartInsert(vo);
	}
	
	@Override
	public int getCartUpdate(Cart_VO vo) throws Exception {
		return shop2_DAO.getCartUpdate(vo);
	}
	
	@Override
	public int getCartEdit(String cart_idx, String p_su) throws Exception {
		return shop2_DAO.getCartEdit(cart_idx, p_su);
	}
	
	@Override
	public int getCartDelete(String cart_idx) throws Exception {
		return shop2_DAO.getCartDelete(cart_idx);
	}
	
	@Override
	public int getProudctInsert(Shop2_VO s2vo) throws Exception {
		return shop2_DAO.getProudctInsert(s2vo);
	}
}







