package com.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryMethodTest {

	@Autowired
	private BoardRepository boardRepo;

	/*
	 * @Before public void dataPrepare() {
	 * 
	 * for(int i =1; i<50; i++) { Board board = new Board();
	 * 
	 * board.setCnt(0L); board.setTitle(i+" 번째 게시글"); board.setWriter(i+"테스트");
	 * board.setContent(i+"번쨰 테스트 내용"); board.setCreateDate(new Date());
	 * boardRepo.save(board); }
	 * 
	 * }
	 */

	/*
	 * //like 연산자 사용 검색
	 * 
	 * @Test public void testFindByTitle() { //List<Board> boardList =
	 * boardRepo.findByTitle("22 번째 게시글");
	 * 
	 * //실행 결과 where 절에서 like연산자 사용 List<Board> boardList =
	 * boardRepo.findByContentContaining("2");
	 * 
	 * System.out.println("검색 결과");
	 * 
	 * for(Board board : boardList) { System.out.println("---->" +
	 * board.toString()); }
	 * 
	 * }
	 */

	/*
	 * //like or And 검색
	 * 
	 * @Test public void testFindByTitleContainingOrContentContaining() {
	 * 
	 * List<Board> list = boardRepo.findByTitleContainingOrContentContaining("16",
	 * "16");
	 * 
	 * for(Board board : list) { System.out.println("====>" + board.toString()); }
	 * 
	 * 
	 * Hibernate: select board0_.seq as seq1_0_, board0_.cnt as cnt2_0_,
	 * board0_.content as content3_0_, board0_.create_date as create_d4_0_,
	 * board0_.title as title5_0_, board0_.writer as writer6_0_ from board board0_
	 * where board0_.title like ? escape ? or board0_.content like ? escape ?
	 * ====>Board(seq=18, title=16 번째 게시글, writer=16테스트, content=16번쨰 테스트 내용,
	 * createDate=2019-08-11, cnt=0)
	 * 
	 * 
	 * }
	 */

	/*
	 * 
	 * //정렬하기 OrderBy+변수 + Asc or Desc
	 * 
	 * @Test public void testOrderby() { List<Board> list =
	 * boardRepo.findByTitleContainingOrderBySeqDesc("2");
	 * 
	 * for(Board board : list) { System.out.println("====>" + board.toString()); } }
	 * 
	 * 
	 */

	/*
	 * //페이징 정렬
	 * 
	 * @Test public void testOrderbyPaging() {
	 * 
	 * //Pageable paging = PageRequest.of(0, 3); //페이지 번호, 검색할 데이터의 개수
	 * 
	 * Pageable paging = PageRequest.of(0, 5,Sort.Direction.DESC,"seq"); //페이징 처리할때
	 * 정렬 //쿼리 메소드에 OrderBy를 결합해도 동일한 결과를 얻을 수 있따.
	 * 
	 * // List<Board> boardList = boardRepo.findByTitleContaining("게시글", paging);
	 * 
	 * //Page<T> Page<Board> pageInfo = boardRepo.findByTitleContaining("게시글",
	 * paging);
	 * 
	 * System.out.println("Page Size : " + pageInfo.getSize());
	 * System.out.println("Total pages : " + pageInfo.getTotalPages());
	 * System.out.println("Total count : " + pageInfo.getTotalElements());
	 * System.out.println("Next : " + pageInfo.nextPageable());
	 * 
	 * List<Board> boardList = pageInfo.getContent();
	 * 
	 * for(Board board : boardList) { System.out.println("====>" +
	 * board.toString()); }
	 * 
	 * 
	 * }
	 * 
	 */

	/*
	 * //위치기반 파라미터
	 * 
	 * @Test public void testQueryAnnotation() { List<Board> boardList =
	 * boardRepo.queryAnnotationTest2("10");
	 * 
	 * 
	 * for(Board board : boardList) { System.out.println("====>" +
	 * board.toString()); }
	 * 
	 * }
	 * 
	 */

	// @Query 특정 변수만 조회
	// List<Objectp[]>
	
	
	@Test
	public void testQueryAnnotationtest3() {
		//List<Object[]> boardList = boardRepo.queryAnnotationTest3("2");

	//	for (Object[] board : boardList) {
	//		System.out.println("====>" + Arrays.toString(board));
			/*
			 * ====>[44, 42 번째 게시글, 42테스트] ====>[34, 32 번째 게시글, 32테스트] ====>[31, 29 번째 게시글,
			 * 29테스트] ====>[30, 28 번째 게시글, 28테스트] ====>[29, 27 번째 게시글, 27테스트] ....
			 * 
			 */

	//	}

	}

	
	
	
	// 네이티브 쿼리
	@Test
	public void testQueryAnnotationtest4() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest4("2");

		// 실행결과를 확인하면 우리가 작성한 SQL구문이 그대로 사용되었다
		for (Object[] board : boardList) {
			System.out.println("====>" + Arrays.toString(board));

		}

	}

}
