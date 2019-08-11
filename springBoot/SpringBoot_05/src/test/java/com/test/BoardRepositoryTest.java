package com.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.Board;
import com.test.persistence.BoardRepository;

//CrudRepository 이용한 기본적인 CRUD 테스트 

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;
	
	//등록

	
	@Test
	public void testInsertBoard() {
		
		Board board = new Board();
		board.setCnt(0L);
		board.setTitle("첫 번째 게시글");
		board.setWriter("테스트");
		board.setContent("등록 합니다다다다다");
		board.setCreateDate(new Date());
		
		//하나의 엔티티를 등록이나 수정할때
		//엔티티 영속성 컨텍스트에 저장하기 위해서는 persist() 사용했었다.
		boardRepo.save(board);
		
	}
	
	
	
	
	/*
	//상세 조회
	@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		System.out.println(board.toString());
		//데이터 하나를 조회하기위해 findById() 사용 그러면 Optional타입의 객체가 리턴
		// Optional의 get()이용하면 영속성 컨텍스트에 저장된 Board객체를 받아낼수 있다.
	}
	*/
	
	
	/*
	//수정
	@Test
	public void testUpdateBoard() {
		
		//영속성 컨텍스트에 올리기위해 조회
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("===1번 게시물 제목 수정");
		board.setTitle("제목을 수정합니다");
		boardRepo.save(board);
		
	}
	*/
	
	/*
	
	//삭제
	@Test
	public void restDeleteBoard() {
		
		//로그확인시 삭제전 삭제할 엔티티를 영속성 컨텍스트에 올리는 select 작업이 먼저 처리된다
		boardRepo.deleteById(1L);
	}
	
	*/
	
	
	
	
	
	
}
