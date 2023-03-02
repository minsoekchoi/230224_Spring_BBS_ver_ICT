package com.ict.shop.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.shop.model.vo.Shop_VO;

@Repository
public class Shop_DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public List<Shop_VO> getShopList(String category){
		return sqlSessionTemplate.selectList("shop.list", category);
	}
	
	public Shop_VO getShopOneList(String idx) {
		return sqlSessionTemplate.selectOne("shop.onelist", idx);
	}
}
