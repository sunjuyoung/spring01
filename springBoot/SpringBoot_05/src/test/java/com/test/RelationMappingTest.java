package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.Board;
import com.test.domain.Member;
import com.test.persistence.BoardRepository;
import com.test.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelationMappingTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	/*
	@Test
	public void testManyToOneInsert() {
		
		//두명의 회원 엔티티 생성 저장
		Member member1 = new Member();
		
		member1.setId("m001");
		member1.setPassword("pw001");
		member1.setName("둘리");
		member1.setRole("User");
		memberRepo.save(member1);
		
		Member member2 = new Member();
		
		member2.setId("m002");
		member2.setPassword("pw002");
		member2.setName("징징");
		member2.setRole("User");
		memberRepo.save(member2);
		
		
		//엔티티를 저장할때 연관관계에 있는 엔티티가 있다면 해당 엔티티도 영속 상태에 있어야한다
		//게시글 과 연관관계가 있는 회원 엔티티를 먼저 영속성컨텍스트에 저장하고 게시글 엔티티 저장
		
		for(int i =1; i<4; i++) {
			Board board1 = new Board();
			board1.setMember(member1);
			board1.setTitle("둘리 게시글 " + i);
			board1.setContent("둘리 글" + i);
			board1.setCreateDate(new Date());
			board1.setCnt(0L);
			boardRepo.save(board1);
			
		}
		
		for(int i =1; i<4; i++) {
			Board board2 = new Board();
			board2.setMember(member2);
			board2.setTitle("징징 게시글 " + i);
			board2.setContent("징징 글" + i);
			board2.setCreateDate(new Date());
			board2.setCnt(0L);
			boardRepo.save(board2);
			
		}
		
	}
	*/
	
	
	//ManyToOne 어노테이션 fetch 속성 기본값은 EAGER:연관 엔티티를 동시에 조회, 따라서 연관관게인 회원 정보까지 조회된다
	//실행결과 조인이 외부조인 outer join으로 연결되어있다
	//외부조인은 성능상 내부조인보다 좋지않다 따라서 반드시 참조 키에 값이 설정된다는 전제가 성립된다면 외부조인을 내부 조인으로 변경하는 것이 좋다
	
	/*
	@Test
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).get();
		
		System.out.println(board.getSeq() + " 번 게시물 정보 ");
		System.out.println("제목" + board.getTitle());
		System.out.println("내용 : " + board.getContent() );
		System.out.println("작성자 : "+ board.getMember().getName());
		System.out.println("작성자 권한 : "+ board.getMember().getRole());
		
	}
	*/
	
	//양방향 연관관계 
	@Test
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("m001").get();
		
		System.out.println(member.getName() + "가 저장한 게시글 목록");
		System.out.println("=============================");
		List<Board> list = member.getBoardList();
		for(Board board : list) {
			System.out.println(board.toString());
		}
		
	}
	
	
	
}
