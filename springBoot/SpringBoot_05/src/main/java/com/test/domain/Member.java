package com.test.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
//StackOverflowError 발생 롬복에서 제공하는 ToString이 양방향 참조에서 상호 호출
//exclude속성을 추가하여 상호 호출 고리를 끊는다
@ToString(exclude="boardList")
@Entity
public class Member {

	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	
	//양방향 매핑설정 ,일대다
	//mappedBy : 양방향 연관관계에서 주인을 지정할때 사용한다
	//fetch기본속성이 LAZY 이므로 EAGER로하여 연관관계도 조회
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER ,cascade = CascadeType.ALL) 
	private List<Board> boardList = new ArrayList<Board>();
	
	
	//객체에는 원래부터 양방향이라는 개념이 없고 서로를 참조하는 단방향 관계 두개가 필요하다
	//하지만 테이블의 경우에는 외래 키하나로 양방향을 조회할수 있따.
	//엔티티를 양방향으로 매핑하려면 매핑에 참여하는 참조 변수는 두 개인데 외래키는 하나이기 때문에 둘 사이에 차이가 발생한다
	//따라서 둘 중 어떤 관계를 사용해서 외래키를 관리할지 결정해야한다 이것을 주인이라고한다
	//연관관계의 주인이 아닌쪽은 자신이 연관관게의 주인이 아님을 알려줘야하는데 이때 사용하는것이 mappedBy 속성이다
	//주인은 테이블에 외래키가 있는곳으로 정해야한다  따라서 Board.member 변수가 주인이 된다
	//반대로 주인이 아닌 Member.boardList에는 mappedBy속성을 사용하여 주인이 아님을 표시
	
	
	
	
}
