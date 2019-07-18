package com.test.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	
	private Long bno;
	
	private String title;
	private String content;
	
	
	private Date regDate,updateDate;
	private String writer;
	

	
	
}
