package com.ksh.board.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ksh.board.dao.BoardDAO;
import com.ksh.board.dto.BoardVO;
import com.ksh.board.dto.PageVO;
import com.ksh.board.service.BoardWriteService;
import com.ksh.common.ServletUpload;

@Service("boardWriteService")
public class BoardWriteServiceImpl implements BoardWriteService {

	@Resource//DI
	private BoardDAO boardDao;
	
	@Resource(name="fileUtils")
	private ServletUpload fileUtils;

	
	@Override
	public PageVO writeArticle(PageVO pvo) {
		// TODO Auto-generated method stub
		
		  if(pvo.getCurrentPage() == 0)
			  pvo.setCurrentPage(1);

		  if(pvo.getCurrPageBlock() == 0)
			  pvo.setCurrPageBlock(1);
		   
		  // num == 0

			return pvo;
		}

	
	
	@Override
	public void writeProArticle(BoardVO bvo,MultipartHttpServletRequest mpRequest) {
			
		int number = boardDao.getNewNum();
		
		if(bvo.getNum() == 0)
		{
			bvo.setNum(number);
			bvo.setRef(number);
			bvo.setRe_level(1);
			bvo.setRe_step(1);
		}
		else
		{
			bvo.setNum(number);
			bvo.setRe_level(bvo.getRe_level() + 1);
			bvo.setRe_step(bvo.getRe_step() + 1);
		}
		
		Map<String,Object> fileMap = null;
		
		try {
			fileMap = fileUtils.parseInsertFileInfo(bvo,mpRequest);
			System.out.println("===================================");
			System.out.println(fileMap.get("fileNo"));
			System.out.println(fileMap.get("org_file_name"));
			System.out.println(fileMap.get("stored_file_name"));
			System.out.println(fileMap.get("fileSize"));
			System.out.println("====================================");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(fileMap!=null)
		{
			bvo.setFileNo((int) fileMap.get("fileNo"));
			bvo.setFileSize((long) fileMap.get("fileSize"));
			bvo.setAttachNm((String) fileMap.get("stored_file_name"));
			bvo.setOrgFileNm((String) fileMap.get("org_file_name"));
		}
		
		boardDao.boardWrite(bvo);

	}
	
	// updatePro
	@Override
	public PageVO updatePro(PageVO pvo, BoardVO bvo,MultipartHttpServletRequest mpRequest){
		if(pvo.getCurrentPage() == 0)
			  pvo.setCurrentPage(1);

		if(pvo.getCurrPageBlock() == 0)
			  pvo.setCurrPageBlock(1);
		
		
		Map<String,Object> fileMap = null;
		
		try {
			fileMap = fileUtils.parseInsertFileInfo(bvo,mpRequest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(fileMap!=null)
		{
			bvo.setFileNo((int) fileMap.get("fileNo"));
			bvo.setFileSize((long) fileMap.get("fileSize"));
			bvo.setAttachNm((String) fileMap.get("stored_file_name")); //afile �� attachNm ���� ... 
			bvo.setOrgFileNm((String) fileMap.get("org_file_name"));
		}
		
		boardDao.boardUpdate(bvo);
		
		return pvo;
	}
	
	// deletePro
	@Override
	public PageVO deletePro(PageVO pvo, int num)
	{
		if(pvo.getCurrentPage() == 0)
			  pvo.setCurrentPage(1);

		if(pvo.getCurrPageBlock() == 0)
			  pvo.setCurrPageBlock(1);
		
		boardDao.deletePro(num);
		return pvo;
	}
}
