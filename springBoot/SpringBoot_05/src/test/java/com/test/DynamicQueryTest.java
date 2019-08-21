package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;
import com.test.domain.Board;
import com.test.domain.QBoard;
import com.test.persistence.DynamicBoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicQueryTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "TITLE";
		String searchKeyword = "10";
		
		
		//가변적인 파라미터 값에 따라 동적으로 AND OR에 해당하는 조건을 추가할수있다
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		}else if (searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%"+searchKeyword + "%"));
		}
		
		
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Board> boardList = boardRepo.findAll(builder,paging);
		
		System.out.println("검색 결과");
		
		for(Board board : boardList) {
			System.out.println("---->" + board.toString());
		}
		
	}
	
	

}
