package com.test.mapper;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;

public interface BoardMapper {
	
	public List<BoardVO> getListWithPaging (Criteria cri);

	public int totalCount (Criteria cri);
}
