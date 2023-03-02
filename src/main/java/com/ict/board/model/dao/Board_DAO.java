package com.ict.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.board.model.vo.Board_VO;

@Repository
public class Board_DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	public int getTotalCount() {
		return sqlSessionTemplate.selectOne("board.count");
	}
	
	public List<Board_VO> getBoardList(int begin, int end) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end",end);
		return sqlSessionTemplate.selectList("board.list", map);
	}
	
	public int getBoardInsert(Board_VO bovo) {
		return sqlSessionTemplate.insert("board.insert", bovo);
	}
	
	public int getBoardHit(String idx) {
		return sqlSessionTemplate.update("board.hitup", idx);
	}
	
	public Board_VO getBoardOneList(String idx) {
		return sqlSessionTemplate.selectOne("board.onelist", idx);
	}
	
	public int getLevUp(Map<String, Integer> map) {
		try {
			return sqlSessionTemplate.update("board.levup", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public int getBoardAnsInsert(Board_VO bovo) {
		try {
			return sqlSessionTemplate.insert("board.ansinsert", bovo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public int getAllDelete(String groups) {
		return sqlSessionTemplate.delete("board.deleteall", groups);
	}
	public int getAnsDelete(String idx) {
		return sqlSessionTemplate.delete("board.ansdelete", idx);
	}
}










