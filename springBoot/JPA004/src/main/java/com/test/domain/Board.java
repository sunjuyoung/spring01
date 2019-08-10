package com.test.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 *
 */
@Entity
//@Table(name="BOARD") //Entity이름과 테이블 이름을 다르게 할 경우 name,catalog,schema,uniqueConstraints
//,uniqueConstraints={@uniqueConstraints(columnNames={"SEQ","WRITER"})}


//ALL_SEQUENCES 키생성 테이블을 만들고 BOARD_SEQ이름으로 증가되는 값을 저장하라는 의미
//initialValue 0 지정했기 때문에 처음 저장되는 번호는 0 ,allocationSize 1 지정했기 때문에 
// ALL_SEQUENCES 테이블에서 값을 한번 꺼낼쓸떄마다 1씩 증 가한다
/*
 * @TableGenerator(name="BOARD_SEQ_GENERATOR", 
 * 					table="ALL_SEQUENCES",
 * 					pkColumnValue="BOARD_SEQ", 
 * 					initialValue=0, allocationSize=1)    // GenerationType.TABLE
 */
@SequenceGenerator(name="BOARD_SEQ_GENERATOR",
					sequenceName="BOARD_SEQUENCE",
					initialValue=1,
					allocationSize=1)
public class Board implements Serializable {


	//@Column은 엔티티의 변수와 테이블의 칼럼을 매핑할때 사용 title=TITLE 칼럼과 자동으로 매핑
	//속성 name 이름 지정 nullable null상태여부 설정 기본값 false
	
	
	
	//@Transient 변수를 영속 필드에서 제외
	
	//@GeneratedValue 기본 값은 hibernate_sequence란는 적절한 이름의 시퀀스를 생성하여 사용한다
	
	@Id
	//@GeneratedValue(strategy = GenerationType.TABLE, generator="BOARD_SEQ_GENERATOR") //@Id가 선언된 필드에 기본 키 값을 자동으로 할당한다.
	//GenerationType.Table : Pk값 생성만을 위한 별도의 테이블이 필요하다
	//GenerationType.SEQUENCE : 시퀀스 이용 시퀀스를 지원하는 데이터베이스에서만 사용할수 있다
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="BOARD_SEQ_GENERATOR")  
	private Long seq;
	private String title;
	//@Column(nullable=false)
	private String writer;
	private String content;
	//Temporal 데이터 타입 매핑할때 사용 TemporalType.DATE,날짜만 출력 , TemporalType.TIME 시간만 출력  TIMESTAMP 모두출력
	@Temporal(TemporalType.DATE)
	private Date createDate;
	private Long cnt;
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createDate=" + createDate + ", cnt=" + cnt + "]";
	}
	
	
   
}
