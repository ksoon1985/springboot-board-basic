package com.ksh.board.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ksh.board.dto.BoardVO;
import com.ksh.board.dto.PageVO;
import com.ksh.board.service.BoardListService;
import com.ksh.board.service.BoardWriteService;
import com.ksh.common.ServletUpload;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//DI(Dependency Injection)
	@Autowired
	private BoardListService boardListService;//getArticle(s): boardList, content
	
	//write에 해당되는 것 
	@Autowired
	private BoardWriteService boardWriteService;//getArticle(s)
	
	/*
	  DI 방법 어노테이션 @Autowired, @inject,    @Resource
	                     지원자   스프링전용        자바에서 지원    자바에서 지원
	                    연결방식 타입에 맞춰서    타입에 맞춰서    이름으로 연결
    @Autowired
    //@Qualifier("BoardWriteServiceImpl")
	private BoardWriteService boardWriteService;
	좀더 강력하다고 함	                    
	 */
	
	 
	//갱신에 관계 된거 , update, delete
	@RequestMapping(value = "/boardList.sp")
	public String boardList(HttpServletRequest request,
			                HttpServletResponse response,  
			                Model model,
			                BoardVO bvo, PageVO pvo) {
		// service를 DI(Dependency Injection) 하고
		// 해당 service의 메소드를 호출한 후
		// model이나 기타 DTO 저장하여 view로 전달
		// 비즈니스 로직을 적지 않음
		pvo.setAllCount(boardListService.getAllcount());
		logger.info("현재페이지: "+pvo.getCurrentPage());
		List<BoardVO> list = boardListService.getArticles(pvo);
		
		/*
		 * logger.info("쩝쩝" +pvo.getAllCount()); logger.info("" +pvo.getAllPage());
		 * logger.info("" +pvo.getCurrentPage()); logger.info(""
		 * +pvo.getCurrPageBlock()); logger.info("" +pvo.getEndPage()); logger.info(""
		 * +pvo.getStartPage()); logger.info("" +pvo.getPageBlock());
		 */
		model.addAttribute("pvo",pvo);
		model.addAttribute("list",list);
		return "board2/boardList";
	}
	
	@RequestMapping(value="/content.sp")
	public String content(HttpServletRequest request,
			HttpServletResponse response,  
			Model model, BoardVO bvo, PageVO pvo) {
			
		Map<String, Object> cmap = 
			boardListService.getArticles(bvo,pvo);
		 /*
		   ModelAndView mav = new ModelAndView();
		   Model도 있고, view 도 있음
		   mav.addObject("pvo", pvo);
   		   mav.setView("board2/content");
   		   return mav;
		  */
		
		  
		
		  model.addAttribute("pvo", (PageVO)cmap.get("pvo"));
		  model.addAttribute("bvo", (BoardVO)cmap.get("bvo"));
		return "board2/content";
	}
	
	@RequestMapping(value="/writeForm.sp")
	public String writeForm(HttpServletRequest request,
            HttpServletResponse response,  
            Model model,BoardVO bvo, PageVO pvo) {
		
		    PageVO pvo2 = boardWriteService.writeArticle(pvo);
		    model.addAttribute("pvo", pvo2);
		    model.addAttribute("bvo", bvo); //받아온걸다시 넣어주기
		return "board2/writeForm";
	}
	@RequestMapping(value="/writePro.sp")
	public String writePro(Model model,BoardVO bvo, PageVO pvo, 
			    MultipartHttpServletRequest mpRequest) {
		
		boardWriteService.writeProArticle(bvo, mpRequest);
 		logger.info("f"+bvo.getFileNo());
		//boardWriteService.writeProArticle(bvo);
		 if(pvo.getCurrentPage()==0) {
			 pvo.setCurrentPage(1); 
		   }
		 if(pvo.getCurrPageBlock()==0) {
			 pvo.setCurrPageBlock(1);
		   }
		 
		model.addAttribute("pvo", pvo);
		
		return "board2/writePro";
	}
	
	@RequestMapping(value="/idCheck.sp")
	public String idCheck() {
		return "board2/idCheck";
	}
	
	@RequestMapping(value="/updateForm.sp")
	public String updateForm(HttpServletRequest request,
			HttpServletResponse response,  
			Model model,BoardVO bvo, PageVO pvo) {
		Map<String, Object> cmap = 
				boardListService.getArticles(bvo,pvo);
		  model.addAttribute("pvo", (PageVO)cmap.get("pvo"));
		  model.addAttribute("bvo", (BoardVO)cmap.get("bvo"));
		return "/board2/updateForm";
	}
	
	//updatePro 처리용 하나 생성 필요
	@RequestMapping(value="/updatePro.sp")
	public String updatePro(MultipartHttpServletRequest mpRequest,  
			Model model,BoardVO bvo, PageVO pvo) {
		
		//서비스 호출
		PageVO pvo2 = boardWriteService.updatePro(pvo, bvo, mpRequest);
		model.addAttribute("pvo", pvo2);
		String urlPage= "currentPage="+pvo2.getCurrentPage()+"&currPageBlock="+pvo2.getCurrPageBlock();
		return "redirect:/boardList.sp?"/* +urlPage */;
	}

	
	@RequestMapping(value="/deletePro.sp")
	public String deletePro(Model model,BoardVO bvo, PageVO pvo) {
		//서비스 호출
		PageVO pvo2 = boardWriteService.deletePro(pvo, bvo.getNum());
		model.addAttribute("pvo", pvo2);
		logger.info("pvo"+pvo2.getCurrentPage());
		String urlPage= "currentPage="+pvo2.getCurrentPage()+"&currPageBlock="+pvo2.getCurrPageBlock();
		return "redirect:/boardList.sp?"/* +urlPage */;
	}
	
	@RequestMapping(value="/fileDown.sp")
	public void fileDown(HttpServletResponse response,Model model,BoardVO bvo, PageVO pvo) {
		try {
		byte fileByte[] = FileUtils.readFileToByteArray(new File(ServletUpload.UPLOAD_DIR+bvo.getAttachNm()));
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(bvo.getOrgFileNm(), "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	  
}
