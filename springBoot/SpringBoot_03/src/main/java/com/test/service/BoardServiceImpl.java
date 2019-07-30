package com.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Override
	public String hello(String name) {
		return "hello it's me" + name;
	}

	@Override
	public BoardVO getBoard() {
		
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setContent("테스트내용입니다");
		board.setTitle("테스트");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}

	@Override
	public List<BoardVO> getBoardList() {
		
		List<BoardVO> list = new ArrayList<>();
		for(int i=0; i<11; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setContent(i + "테스트내용입니다");
			board.setTitle(i + "테스트");
			board.setCreateDate(new Date());
			board.setCnt(0);
			
			
			list.add(board);
			
		}
		
		
		
		return list;
	}

}
