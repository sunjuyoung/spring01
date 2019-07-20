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

	log.info("ReplyList....service..");
		
		return mapper.getListWithPaging(bno);
	}

	@Override
	public int delete(int rno) {
		
		log.info("reply delete service");
		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("reply modify service");
		return mapper.modify(vo);
	}

	@Override
	public int insert(ReplyVO vo) {
		
		log.info("reply insert service");
		return mapper.insert(vo);
	}

}
