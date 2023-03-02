package com.ict.shop.model.service;

import java.util.List;

import com.ict.shop.model.vo.Shop_VO;

public interface Shop_Service {
	public List<Shop_VO> getShopList(String category) throws Exception;
	public Shop_VO getShopOneList(String idx) throws Exception;
	
}
