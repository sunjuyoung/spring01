package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.ReplyVO;
import com.test.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public List<ReplyVO> getListWithPaging(Long bno) {

		
		return mapper.getListWithPaging(bno);
	}

}
