package com.ksh.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ksh.board.dao.BoardDAO;
import com.ksh.board.dto.BoardVO;
import com.ksh.board.dto.PageVO;
import com.ksh.board.service.BoardListService;

@Service("boardListService")
public class BoardListServiceImpl implements BoardListService {

	private static final Logger logger = LoggerFactory.getLogger(BoardListServiceImpl.class);
	
	@Resource//DI
	BoardDAO boardDao;
	
	@Override
	public int getAllcount()
	{
		return boardDao.getAllcount();
	}
	

	@Override
	public List<BoardVO> getArticles(PageVO pvo) {
		// TODO Auto-generated method stub
	       
	        
	        if(pvo.getAllCount()%pvo.getLinePerPage()==0) {
	        	
	        	pvo.setAllPage(pvo.getAllCount()/pvo.getLinePerPage());   
	        }
	        else{
	        	
	        	pvo.setAllPage(pvo.getAllCount()/pvo.getLinePerPage()+1);
	        }
	        
	        
	        if(pvo.getCurrentPage() == 0)
	        {
	        	pvo.setCurrentPage(1);
	        }
	        
	        if(pvo.getCurrPageBlock() == 0)
	        {
	        	pvo.setCurrPageBlock(1);
	        }
	        
	        int startPage = 1;
	        int endPage = 1;
	        
	        
	      /*  if(pvo.getAllPage() < pvo.getPageBlock()) {
	           startPage = 1;
	           endPage= pvo.getAllPage();
	        }
	        else 
	        {
	           */
	        		
	        	startPage = (pvo.getCurrPageBlock() - 1) * pvo.getPageBlock() + 1;
	        	endPage = pvo.getCurrPageBlock() * pvo.getPageBlock() > pvo.getAllPage() ? 
	        			pvo.getAllPage() : pvo.getCurrPageBlock() * pvo.getPageBlock();
	        
	        pvo.setStartPage(startPage);
	        pvo.setEndPage(endPage);
	        
	        
	        int start = (pvo.getCurrentPage() -1) * pvo.getLinePerPage() + 1;
	        int end = pvo.getCurrentPage() * pvo.getLinePerPage();
	        
	        Map<String,Integer> hmap = new HashMap<String,Integer>();
	        hmap.put("start",start);
	        hmap.put("end",end);
	        
	        List<BoardVO> list 
	           = boardDao.getArticles(hmap);
	        

		return list;
	}
	
	
	@Override
	public Map<String,Object> getArticles(BoardVO bvo2,PageVO pvo)
	{
	
        if(pvo.getCurrentPage() == 0)
        {
        	pvo.setCurrentPage(1);
        }
        
        if(pvo.getCurrPageBlock() == 0)
        {
        	pvo.setCurrPageBlock(1);
        }
        
        
        BoardVO bvo = boardDao.getArticles(bvo2); 
        boardDao.updateReadCount(bvo);
        
        Map<String,Object> cmap = new HashMap<String,Object>();
        cmap.put("pvo", pvo);
        cmap.put("bvo", bvo);
        
		return cmap;
	}
	
}
