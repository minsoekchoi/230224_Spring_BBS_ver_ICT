package com.ict.shop.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.shop.model.dao.Shop_DAO;
import com.ict.shop.model.vo.Shop_VO;

@Service
public class Shop_ServiceImpl implements Shop_Service{
	@Autowired
	private Shop_DAO shop_DAO;
	public void setShop_DAO(Shop_DAO shop_DAO) {
		this.shop_DAO = shop_DAO;
	}
	
	
	@Override
	public List<Shop_VO> getShopList(String category) throws Exception {
		return shop_DAO.getShopList(category);
	}
	@Override
	public Shop_VO getShopOneList(String idx) throws Exception {
		return shop_DAO.getShopOneList(idx);
	}
}
