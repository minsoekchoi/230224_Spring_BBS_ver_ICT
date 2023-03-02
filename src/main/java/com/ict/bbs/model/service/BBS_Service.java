package com.ict.bbs.model.service;

import java.util.List;

import com.ict.bbs.model.vo.BBS_VO;
import com.ict.bbs.model.vo.Comment_VO;

public interface BBS_Service {
	public int getTotalCount() ;
	public List<BBS_VO> getList(int begin, int end);
	public BBS_VO getOneList(String b_idx);
	public int getHitUpdate(String b_idx);
	public int getInsert(BBS_VO bvo);
	public List<Comment_VO> getCommList(String b_idx);
	public int getCommWrite(Comment_VO cvo);
	public int getCommDelete(String c_idx);
	public int getCommDelete2(String b_idx);
	public int getDelete(String b_idx);
}
