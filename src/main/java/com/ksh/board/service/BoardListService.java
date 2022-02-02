package com.ksh.board.service;

import java.util.List;
import java.util.Map;

import com.ksh.board.dto.BoardVO;
import com.ksh.board.dto.PageVO;

public interface BoardListService {
	public int getAllcount();
	public List<BoardVO> getArticles(PageVO pvo);
	public Map<String,Object> getArticles(BoardVO bvo2,PageVO pvo);
}
