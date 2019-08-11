package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.Board;
import com.test.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryMethodTest {


	@Autowired
	private BoardRepository boardRepo;
	
/*	
	@Before
	public void dataPrepare() {
		
		for(int i =1; i<50; i++) {
			Board board = new Board();
		
			board.setCnt(0L);
			board.setTitle(i+" 번째 게시글");
			board.setWriter(i+"테스트");
			board.setContent(i+"번쨰 테스트 내용");
			board.setCreateDate(new Date());
			boardRepo.save(board);
		}
		
	}
	*/
	@Test
	public void testFindByTitle() {
		//List<Board> boardList = boardRepo.findByTitle("22 번째 게시글");
		
		//실행 결과 where 절에서 like연산자 사용
		List<Board> boardList = boardRepo.findByContentContaining("2");
		
		System.out.println("검색 결과");
		
		for(Board board : boardList) {
			System.out.println("---->" + board.toString());
		}
		
	}
	
	
}
