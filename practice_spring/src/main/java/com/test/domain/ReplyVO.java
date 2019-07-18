package com.test.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyVO {
	
	private Long bno,rno;
	
	private String reply;
	private String replyer;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date replyDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date updateDate;
	
	
	
}
