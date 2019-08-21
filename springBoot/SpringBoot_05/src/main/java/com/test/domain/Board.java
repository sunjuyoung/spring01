package com.test.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="member")
@Entity
public class Board {

	@Id
	@GeneratedValue
	private Long seq;
	private String title;
	//@Column(nullable=false)
//	private String writer;
	private String content;
	//Temporal 데이터 타입 매핑할때 사용 TemporalType.DATE,날짜만 출력 , TemporalType.TIME 시간만 출력  TIMESTAMP 모두출력
	@Temporal(TemporalType.DATE)
	private Date createDate;
	private Long cnt;
	
	
	//객체를 양방햔 연관관계로 만들면 주인을 정해야한다
	//일반적으로 일대다에서 다쪽에 해당하는 쪽이 주인이라 생각하면 쉽다
	
	
	@ManyToOne//다대일 관계 설정 (단방향매핑)
	@JoinColumn(name="MEMBER_ID",nullable=false) // 외래키 매핑, MEMBER_ID컬럼이 항상 참조 값을 가진다 의미로 nullable속성추가(내부조인 검색)
	private Member member;
	
	
	//게시판 객체에 회원 객체를 설정할때 회원이 소유한 게시 글 컬렉션에 자신(게시글)도 자동으로 저장될 수 있도록
	//이렇게 해야 영속 객체가 아닌 단순한 일반 자바 객체 상태에서도 관련된 데이터를 사용할 수 있다.
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
}
