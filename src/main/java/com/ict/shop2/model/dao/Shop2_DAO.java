package com.ict.shop2.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.shop2.model.vo.Cart_VO;
import com.ict.shop2.model.vo.Shop2_VO;

@Repository
public class Shop2_DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public List<Shop2_VO> getShop2List(String category){
		return sqlSessionTemplate.selectList("shop2.list", category);
	}
	
	public Shop2_VO getShop2OneList(String idx) {
		return sqlSessionTemplate.selectOne("shop2.onelist", idx);
	}
	
	public List<Cart_VO> getCartList(String m_id){
		return sqlSessionTemplate.selectList("shop2.cartlist",m_id);
	}
	
	public Shop2_VO getProductOneList(String idx) {
		return sqlSessionTemplate.selectOne("shop2.onelist", idx);
	}
	
	public Cart_VO getCartOneList(String m_id, String p_num) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("m_id", m_id);
		map.put("p_num", p_num);
		
		return sqlSessionTemplate.selectOne("shop2.cartonelist", map);
	}
	
	public int getCartInsert(Cart_VO vo) {
		return sqlSessionTemplate.insert("shop2.cartinsert", vo);
	}
	
	public int getCartUpdate(Cart_VO vo) {
		return sqlSessionTemplate.update("shop2.cartupdate", vo);
	}
	
	public int getCartEdit(String cart_idx, String p_su) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cart_idx", cart_idx);
		map.put("p_su", p_su);
		return sqlSessionTemplate.update("shop2.cartedit", map);
	}

	public int getCartDelete(String cart_idx) {
		return sqlSessionTemplate.delete("shop2.cartdelete", cart_idx);
	}
	
	public int getProudctInsert(Shop2_VO s2vo) {
		return sqlSessionTemplate.insert("shop2.productinsert", s2vo);
	}
}

















