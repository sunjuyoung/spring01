package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.BoardVO;
import com.test.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	private BoardService service;
	
	public BoardController() {
		System.out.println("=====>BoardController 생성");
	}

	// 문자열 리턴
	@GetMapping("/hello")
	public String hello(String name) {
		
		service.hello(name);
		
		return service.hello(name);
	}

	// VO객체리턴
	@GetMapping("/getBoard")
	public BoardVO getBoard() {


		return service.getBoard();

	}

	// 컬렉션 리턴
	@GetMapping("/listBoard")
	public List<BoardVO> listBoard() {



		return service.getBoardList();
	}
	
	
	

}
