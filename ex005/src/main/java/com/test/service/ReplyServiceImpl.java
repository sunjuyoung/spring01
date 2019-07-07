package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.Criteria;
import com.test.domain.ReplyPageDTO;
import com.test.domain.ReplyVO;
import com.test.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int insert(ReplyVO vo) {
		log.info("insert................" + vo);
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyPageDTO getListWithPaging(Long bno, Criteria cri) {
		
		log.info("list................" + bno);
		log.info("REPLYlist................" + cri.getPageNum());
		log.info("list................" + cri.getAmount());
		return new ReplyPageDTO(mapper.getCountByBno(bno),mapper.getListWithPaging(bno, cri));
	}

	@Override
	public int update(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		
		log.info("remove................" + rno);
		
		return mapper.remove(rno);
	}

	@Override
	public ReplyVO read(Long rno) {
		return mapper.read(rno);
	}

}
