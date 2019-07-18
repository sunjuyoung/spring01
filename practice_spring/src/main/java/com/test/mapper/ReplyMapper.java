package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.domain.ReplyVO;

public interface ReplyMapper {
	
	
	public List<ReplyVO> getListWithPaging(@Param("bno")Long bno);
	

}
