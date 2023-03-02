package com.ict.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.model.service.Board_Service;
import com.ict.board.model.vo.Board_VO;
import com.ict.common.FileReName;
import com.ict.common.Paging;
@Controller
public class Board_Controller {
	@Autowired
	private Board_Service board_Service;
	@Autowired
	private Paging paging;
	@Autowired
	private FileReName fileReName;
	
	public void setBoard_Service(Board_Service board_Service) {
		this.board_Service = board_Service;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public void setFileReName(FileReName fileReName) {
		this.fileReName = fileReName;
	}
	
	@RequestMapping("board_list.do")
	public ModelAndView getBoardList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("board/board_list");
		int count = board_Service.getTotalCount();
		paging.setTotalRecord(count);
		
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if(paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage()+1);
			}
		}
		String cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		paging.setBegin((paging.getNowPage() - 1) * paging.getNumPerPage() + 1);
		paging.setEnd((paging.getBegin() - 1) + paging.getNumPerPage());

		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);
		
		if(paging.getEndBlock()>paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		List<Board_VO> board_list 
			= board_Service.getBoardList(paging.getBegin(), paging.getEnd());
		
		mv.addObject("board_list", board_list);
		mv.addObject("paging", paging);
		return mv;
	}
	
	@RequestMapping("board_write.do")
	public ModelAndView getWrite() {
		return new ModelAndView("board/board_write");
	}
	
	@RequestMapping("board_write_ok.do")
	public ModelAndView getWriteOK(Board_VO bovo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:board_list.do");
		String path = session.getServletContext().getRealPath("/resources/upload");
		MultipartFile f_param = bovo.getF_param();
		try {
			if(f_param.isEmpty()) {
				bovo.setF_name("");
			}else {
				// 이름중복여부
				String str = fileReName.exec(path, bovo.getF_param().getOriginalFilename());
				bovo.setF_name(str);
			}
			mv.addObject("cPage", "1");
			int res = board_Service.getBoardInsert(bovo);
			if(res>0) {
				f_param.transferTo(new File(path+"/"+bovo.getF_name()));
			}
		} catch (Exception e) {
		}
		return mv;
	}
	
	@RequestMapping("board_view.do")
	public ModelAndView getBoardView(@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("board/board_view");
		// hit 업데이트
		int hit = board_Service.getBoardHit(idx);
		
		// 상세보기
		Board_VO bovo = board_Service.getBoardOneList(idx);
		
		mv.addObject("bovo", bovo);
		return mv;
	}
	
	@RequestMapping("board_download.do")
	public void boardFileDown(@RequestParam("f_name")String f_name,
			HttpSession session, HttpServletResponse response) {
		try {
			String path = session.getServletContext().getRealPath("/resources/upload/"+f_name);
			String r_path = URLEncoder.encode(path, "utf-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="+r_path);
			
			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
		}
	}
	
	@RequestMapping("board_ans_write.do")
	public ModelAndView boardAnsWrite(@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx) {
		return new ModelAndView("board/board_ans_write");
	}
	@RequestMapping("board_ans_write_ok.do")
	public ModelAndView boardAnsWriteOk(@ModelAttribute("cPage") String cPage,
			@RequestParam("idx") String idx,
			Board_VO bovo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:board_list.do");
		try {
			// 상세보기
			Board_VO bovo2 = board_Service.getBoardOneList(idx);
			
			int groups = Integer.parseInt(bovo2.getGroups());
			int step = Integer.parseInt(bovo2.getStep());
			int lev = Integer.parseInt(bovo2.getLev());
			
			step ++ ;
			lev ++;
		
			// DBdp groups, lev를 업데이트해야 된다.
			// groups와 같은 원글을 찾아서 레벨이 같거나 크면, 레벨 증가 
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			int result = board_Service.getLevUp(map);
			
			bovo.setGroups(String.valueOf(groups));
			bovo.setStep(String.valueOf(step));
			bovo.setLev(String.valueOf(lev));
			
			// 댓글 삽입
			String path = session.getServletContext().getRealPath("/resources/upload");
			MultipartFile f_param = bovo.getF_param();
			if(f_param.isEmpty()) {
				bovo.setF_name("");
			}else {
				// 이름중복여부
				String str = fileReName.exec(path, bovo.getF_param().getOriginalFilename());
				bovo.setF_name(str);
			}
			int res = board_Service.getBoardAnsInsert(bovo);
			if(res>0) {
				f_param.transferTo(new File(path+"/"+bovo.getF_name()));
			}
			
		} catch (Exception e) {
		}
		return mv;
	}
	
	@RequestMapping("board_delete.do")
	public ModelAndView boardDelete(@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx, @ModelAttribute("pwd") String pwd) {
		return new ModelAndView("board/board_delete");
	}
	@RequestMapping("board_delete_ok.do")
	public ModelAndView boardDeleteOk(@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx) {
		// 상세보기
		Board_VO bovo2 = board_Service.getBoardOneList(idx);
		
		// 원글일 경우와 댓글 일 경우 
		if(bovo2.getStep().equals("0")) {
			// 댓글 까지 모두 삭제
			// select rownum r_num, a.* from  (select idx, title, groups, step, lev 
			// from board where groups = 4 and step =1  order by lev asc) a
			
			int result = board_Service.getAllDelete(bovo2.getGroups());
		}else {
			// 댓글만 삭제 
			int result2 = board_Service.getAnsDelete(idx);
		}
		return new ModelAndView("redirect:board_list.do");
	}
}





