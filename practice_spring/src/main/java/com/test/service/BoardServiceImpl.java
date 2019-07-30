package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		
		
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int totalCount(Criteria cri) {
		return mapper.totalCount(cri);
	}

	@Override
	public BoardVO get(BoardVO vo) {
		
		log.info("get : " + vo);
		return mapper.get(vo);
	}

	@Override
	public int insert(BoardVO vo) {
		log.info("insert : " + vo);
		return mapper.newBoard(vo);
	}

	@Override
	public int remove(Long bno) {
		log.info("remove : " + bno);
		return mapper.remove(bno);
	}

	@Override
	public int update(BoardVO vo) {
		log.info("modify : " + vo);
		return mapper.update(vo);
	}

	
	
	
}
