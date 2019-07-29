package com.test.mapper;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;

public interface BoardMapper {
	
	public List<BoardVO> getListWithPaging (Criteria cri);

	public int totalCount (Criteria cri);
	
	public BoardVO get(BoardVO vo);
	
	public int newBoard(BoardVO vo);
	
	public int remove (Long bno);
	
	public int update (BoardVO vo);
	
}
