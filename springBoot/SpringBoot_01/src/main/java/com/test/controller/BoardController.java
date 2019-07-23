package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.BoardVO;

@RestController
public class BoardController {

	public BoardController() {
		System.out.println("=====>BoardController 생성");
	}

	// 문자열 리턴
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}

	// VO객체리턴
	@GetMapping("/getBoard")
	public BoardVO getBoard() {

		BoardVO board = new BoardVO();

		board.setCnt(11);
		board.setContent("hihihiihi");
		board.setWriter("hi");
		return board;

	}

	// 컬렉션 리턴
	@GetMapping("/listBoard")
	public List<BoardVO> listBoard() {

		List<BoardVO> vo = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			BoardVO board = new BoardVO();
			board.setCnt(i);
			board.setContent("hihihiihi");
			board.setWriter("hi" + i);

			vo.add(board);
		}

		return vo;
	}
	
	
	

}
