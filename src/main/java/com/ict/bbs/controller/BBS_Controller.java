package com.ict.bbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.bbs.model.service.BBS_Service;
import com.ict.bbs.model.vo.BBS_VO;
import com.ict.bbs.model.vo.Comment_VO;
import com.ict.common.FileReName;
import com.ict.common.Paging;

@Controller
public class BBS_Controller {
	String cPage ;

	@Autowired
	private BBS_Service bbs_Service;
	@Autowired
	private Paging paging;
	@Autowired
	private FileReName fileReName;
	
	public void setBbs_Service(BBS_Service bbs_Service) {
		this.bbs_Service = bbs_Service;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public void setFileReName(FileReName fileReName) {
		this.fileReName = fileReName;
	}
	
	@RequestMapping("bbs_list.do")
	public ModelAndView bbsList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("bbs/bbs_list");
		// 전체 게시물의 수
		int count = bbs_Service.getTotalCount();
		paging.setTotalRecord(count);
		
		// 전체 페이지의 수
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if(paging.getTotalRecord() % paging.getNumPerPage()  != 0) {
				paging.setTotalPage(paging.getTotalPage()+1);
			}
		}
		
		// 현재 페이지
		cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// 시작번호와 끝번호 구하기 
		paging.setBegin((paging.getNowPage()-1)*paging.getNumPerPage()+1);
		paging.setEnd((paging.getBegin()-1)+paging.getNumPerPage());
		
		// 시작 블록과 끝블록 구하기
		paging.setBeginBlock((int)((paging.getNowPage()-1)/paging.getPagePerBlock()) 
				* paging.getPagePerBlock() + 1 );
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock()-1);
		
		// 주의 사항 
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		
		List<BBS_VO> bbs_list = bbs_Service.getList(paging.getBegin(), paging.getEnd());
		mv.addObject("bbs_list", bbs_list);
		mv.addObject("paging", paging);
		return mv;
	}
	
	@RequestMapping("bbs_onelist.do")
	public ModelAndView bbsOneList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("bbs/bbs_onelist");
		String b_idx = request.getParameter("b_idx");
		cPage = request.getParameter("cPage");
		
		// 조회수 업데이트 
		int res = bbs_Service.getHitUpdate(b_idx);
		
		// 상세보기 
		BBS_VO bvo = bbs_Service.getOneList(b_idx);
		
		// 댓글 가져오기 
		List<Comment_VO> c_list = bbs_Service.getCommList(b_idx);
		
		mv.addObject("bvo", bvo);
		mv.addObject("c_list", c_list);
		mv.addObject("cPage", cPage);
		return mv;
	}
	
	@RequestMapping("com_write.do")
	public ModelAndView commWrite(Comment_VO cvo, 
			@ModelAttribute("cPage")String cPage, @ModelAttribute("b_idx")String b_idx) {
		ModelAndView mv = new ModelAndView("redirect:bbs_onelist.do");
		int result = bbs_Service.getCommWrite(cvo);
		return mv;
	}
	
	@RequestMapping("com_del.do")
	public ModelAndView commDelete(@ModelAttribute("cPage")String cPage, 
			@ModelAttribute("b_idx")String b_idx, @RequestParam("c_idx")String c_idx) {
		ModelAndView mv = new ModelAndView("redirect:bbs_onelist.do");
		int result = bbs_Service.getCommDelete(c_idx);
		return mv;
	}
	
	@RequestMapping("bbs_write.do")
	public ModelAndView bbsWrite() {
		ModelAndView mv = new ModelAndView("bbs/bbs_write");
		return mv;
	}
	
	@RequestMapping("bbs_write_ok.do")
	public ModelAndView bbsWriteOK(BBS_VO bvo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:bbs_list.do");
		String path = session.getServletContext().getRealPath("/resources/upload");
		MultipartFile file_name = bvo.getF_param();
		try {
			if(file_name.isEmpty()) {
				bvo.setF_name("");
			}else {
				// 이름 중복 여부
			  String str =	fileReName.exec(path, bvo.getF_param().getOriginalFilename());
			  bvo.setF_name(str);
			}
			mv.addObject("cPage","1");
			int res = bbs_Service.getInsert(bvo);
			if(res>0) {
				file_name.transferTo(new File(path+"/"+bvo.getF_name()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mv;
	}
	
	@RequestMapping("bbs_download.do")
	public void bbsFileDown(@RequestParam("f_name")String f_name,
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
	
	@RequestMapping("bbs_delete.do")
	public ModelAndView bbsDelete(@ModelAttribute("b_idx") String b_idx, 
			@ModelAttribute("pwd") String pwd,
			@ModelAttribute("cPage") String cPage) {
		return new ModelAndView("bbs/bbs_delete");
		
	}
	
	@RequestMapping("bbs_delete_ok.do")
	public ModelAndView bbsDeleteOK(@ModelAttribute("b_idx") String b_idx, 
			@ModelAttribute("cPage") String cPage) {
		// 댓글 삭제
		int result = bbs_Service.getCommDelete2(b_idx);
		   
		// 원글 삭제
		int result2 = bbs_Service.getDelete(b_idx);
		return new ModelAndView("redirect:bbs_list.do");
	}
	
	@RequestMapping("bbs_update.do")
	public ModelAndView bbsUpdate(@ModelAttribute("b_idx") String b_idx, 
			@ModelAttribute("pwd") String pwd,
			@ModelAttribute("cPage") String cPage) {
		
		ModelAndView mv = new ModelAndView("bbs/bbs_update");
		BBS_VO bvo = bbs_Service.getOneList(b_idx);
		mv.addObject("bvo", bvo);
		return mv;
		
	}
}
