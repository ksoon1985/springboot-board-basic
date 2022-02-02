package com.ksh.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ksh.board.dto.BoardVO;

@Mapper
public interface BoardDAO {
	public int getAllcount();
	public List<BoardVO> getArticles(Map<String, Integer> hmap);
	public BoardVO getArticles(BoardVO bvo);
	public void boardWrite(BoardVO bvo);
	public void boardUpdate(BoardVO bvo);
	public void updateReadCount(BoardVO bvo);
	public int getNewNum();
	public void deletePro(int num);
}