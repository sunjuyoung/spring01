package com.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.BooleanBuilder;
import com.test.domain.QBoard;
import com.test.persistence.DynamicBoardRepository;

public class DynamicQueryTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "TITLE";
		String searchKeyword = "10 번째 게시글";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			
		}
	}

}
