package com.test.service;

import java.util.List;

import com.test.domain.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> getListWithPaging(Long bno);
	
	public int delete (int rno);
	
	public int modify(ReplyVO vo);
	
	public int insert(ReplyVO vo);

}
