package com.test.service;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getListWithPaging (Criteria cri);
	
	public int totalCount (Criteria cri);
	
	public BoardVO get(BoardVO vo);
	
	public int insert(BoardVO vo);
	
	public int remove (Long bno);
	
	public int update (BoardVO vo);
	

}
