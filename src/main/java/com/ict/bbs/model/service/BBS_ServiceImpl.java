package com.ict.bbs.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.bbs.model.dao.BBS_DAO;
import com.ict.bbs.model.vo.BBS_VO;
import com.ict.bbs.model.vo.Comment_VO;

@Service
public class BBS_ServiceImpl implements BBS_Service{
	@Autowired
	private BBS_DAO bBS_DAO;

	public void setbBS_DAO(BBS_DAO bBS_DAO) {
		this.bBS_DAO = bBS_DAO;
	}
	
	@Override
	public int getTotalCount() {
		return bBS_DAO.getTotalCount();
	}
	
	@Override
	public List<BBS_VO> getList(int begin, int end) {
		return bBS_DAO.getList(begin, end);
	}
	@Override
	public int getHitUpdate(String b_idx) {
		return bBS_DAO.getHitUpdate(b_idx);
	}
	@Override
	public BBS_VO getOneList(String b_idx) {
		return bBS_DAO.getOneList(b_idx);
	}
	@Override
	public List<Comment_VO> getCommList(String b_idx) {
		return bBS_DAO.getCommList(b_idx);
	}
	
	@Override
	public int getCommWrite(Comment_VO cvo) {
		return bBS_DAO.getCommWrite(cvo);
	}
	
	@Override
	public int getCommDelete(String c_idx) {
		return bBS_DAO.getCommDelete(c_idx);
	}
	
	@Override
	public int getInsert(BBS_VO bvo) {
		return bBS_DAO.getInsert(bvo);
	}
	
	@Override
	public int getCommDelete2(String b_idx) {
		return bBS_DAO.getCommDelete2(b_idx);
	}
	@Override
	public int getDelete(String b_idx) {
		return bBS_DAO.getDelete(b_idx);
	}
	
}
