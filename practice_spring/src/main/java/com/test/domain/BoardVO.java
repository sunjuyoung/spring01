package com.test.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {

	
	private Long bno;
	
	private String title;
	private String content;
	
	
	private Date regDate;
	private Date updateDate;
	
	private String writer;
	

	private String userid;
	
	private MemberVO member;
	
	
	
}
