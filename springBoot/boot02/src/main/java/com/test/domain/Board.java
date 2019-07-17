package com.test.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity //해당 클래스의 인스턴스들이 엔티티임을 명시합니다.
@Table(name="tb1_boards")
public class Board {

	
	//@Id 칼럼이 식별키라는 것을 의미 
	//주로 @GeneratedValue라는 어노테이션과 같이 이용해서 식별키를 어떤 전략으로 생성하는지 명시합니다.
	//@GeneratedValue 는 strategy : AUTO,TABLE,SEQUENCE,IDENTITY
	//,generator : @TableGenerator, @SequenceGenerator 속성으로 구분됩니다
	/*
	 * AUTO 특정 데이터베이스에 맞게 자동으로 생성되는 방식 IDENTITY 기보키 생성방식 자체를 데이터베이스에 위임, 데이터베이스에
	 * 의존적인 방식, MYSQL에서 주로 많이 사용 SEQUENCE 데이터베이스의 시퀸스를 이용해서 식별키 생성 오라클에서 사용 TABLE
	 * 별도의 키를 생성해 주는 채번 테이블을 이용하는 방식
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate; //LocalDateTime
	@UpdateTimestamp
	private Timestamp updatedate; //LocalDateTime

}
