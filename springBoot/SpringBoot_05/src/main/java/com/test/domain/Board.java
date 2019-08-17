package com.test.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id
	@GeneratedValue
	private Long seq;
	private String title;
	//@Column(nullable=false)
	private String writer;
	private String content;
	//Temporal 데이터 타입 매핑할때 사용 TemporalType.DATE,날짜만 출력 , TemporalType.TIME 시간만 출력  TIMESTAMP 모두출력
	@Temporal(TemporalType.DATE)
	private Date createDate;
	private Long cnt;
	
	private int hi;
	
}
