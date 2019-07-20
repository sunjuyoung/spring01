package com.test.service;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getListWithPaging (Criteria cri);
	
	public int totalCount (Criteria cri);
	
	public BoardVO get(BoardVO vo);

}
