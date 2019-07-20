package com.test.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {

	
	private Long bno;
	
	private String title;
	private String content;
	
	
	private Date regDate;
	private Date updateDate;
	
	private String writer;
	

	
	
}
