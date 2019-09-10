package com.test.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {

	
	@Autowired
	private BoardMapper mapper;
	/*
	@Test
	public  void test() {

		mapper.boardList().forEach(s->System.out.println(s));
	}
	
	*/
	
	/*
	//insert
	@Test
	public void insertTest() {
		BoardVO vo = new BoardVO();
		vo.setContent("t");
		vo.setTitle("e");
		vo.setWriter("test");
		
		
		mapper.insertSelectKey(vo);
	}
	
	*/
	
	/*
	//삭제
	@Test
	public void removeTest() {
		
		
		
		mapper.delete(33903L);
	}
	
	*/
	
	
	
	@Test
	public void updateTest() {
		BoardVO vo = new BoardVO();
		vo.setBno(249L);
		vo.setContent("ttesttesttesttest");
		vo.setTitle("testtesttesttest");
	
		
		mapper.update(vo);
	}
	
}
