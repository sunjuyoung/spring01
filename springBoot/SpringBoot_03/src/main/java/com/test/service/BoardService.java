package com.test.service;

import java.util.List;

import com.test.domain.BoardVO;

public interface BoardService {
	
	String hello(String name);
	
	BoardVO getBoard();
	
	List<BoardVO> getBoardList();
	

}
