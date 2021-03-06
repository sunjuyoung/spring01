package com.test.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class ReplyMapperTests {

	
	@Autowired
	private ReplyMapper mapper;
	
	private Long bno = 33946L;
	/*
	
	@Test
	public void testInsert() {
		
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(bno);
		vo.setReply("test");
		vo.setReplyer("tester");
		
		mapper.insert(vo);
	}
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	*/
	
	
	@Test
	public void updateTest() {
		
		ReplyVO vo = new ReplyVO();
		
		vo.setReply("수정 테스트입니다");
		vo.setRno(141L);
	
		mapper.modify(vo);
	}
	
}
