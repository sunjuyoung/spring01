package com.test.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.BoardVO;
import com.test.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Autowired
	private DataSource ds;

	PreparedStatement pstmt = null;
	Connection conn = null;

	/*
	 * @Test
	 * public void updateTest() {
	 * 
	 * String sql = "";
	 * 
	 * try {
	 * conn = ds.getConnection();
	 * pstmt = conn.prepareStatement(sql);
	 * 
	 * pstmt.executeUpdate();
	 * 
	 * } catch (Exception e) {
	 * 
	 * }
	 * 
	 * }
	 */
	
	
	@Test
	public void insertTest() {
		
		BoardVO vo = new BoardVO();
		
		vo.setContent("테스트 입니다");
		vo.setTitle("test");
		vo.setUserid("user1");
		vo.setWriter("일반사용자1");
		
		mapper.newBoard(vo);
		
		
	}
	
	
		
		
		
		

}
