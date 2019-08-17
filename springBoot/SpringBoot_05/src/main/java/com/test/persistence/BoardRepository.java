package com.test.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.domain.Board;

//Repository - CrudRepository - pagingAndSortingRepository - Jparepository
//가장 상위 Repository는 기능이 거의 없으므로 일반적으로 CrudRepository인터페이스를 사용
//모든 인터페이스들은 공통적으로 두 개의 제네릭 타입을 지정하도록 되어있다.
//CrudRepository<T,ID> 엔티티의 클래스타입, 식별자타입(@Id로 매핑한 식별자 변수의 타입)
public interface BoardRepository extends CrudRepository<Board,Long>{
	//스프링 데이터 JPA 별도의 구현 클래스를 만들지 않고 인터페이스마 정의함으로써 기능을 사용할 수 있다.
	// 스프링 부트가 내부적으로 인터페이스에 대한 구현 객체를 자동으로 생성해준다
	//EntityManagerFactory, EntityManager, EntityTransaction 같은 객체도 필요 없다
	
	
	//게시글  제목으로 목록을 조회하는 findByTitle()
	List<Board> findByTitle(String searchKeyword);
	
	//Like연산자 특정 단어가 포함된 글 목록 조회 Containing 키워드를 사용한다.
	List<Board> findByContentContaining(String content);
	
	
	//여러 조건사용하기 And나 Or 키워드를 결합하여 사용한다
	//제목 혹은 내용에 특정 단어가 포함된 글 목록을 조회하기 위해
	List<Board> findByTitleContainingOrContentContaining(String title,String content); 
	//매개변수 반드시 조건에 맞게해야 한다 , 검색어가 동일하다 하더라도 하나의 변수로 처리할 수 없다.
	
	
	//데이터 정렬
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	
	//모든 쿼리메소드는 마지막 파라미터로 페이징 처리를 위한 Pageable 인터페이스와 정렬을 처리하는 Sort인터페이스를 추가할 수 있따.
	//페이징 처리
	//한 화면에 다섯 개의 데이터를 보여주기로 하고 첫 페이지에 해당하는 1번부터 다섯개의 데이터만 조회
	//List<Board> findByTitleContaining(String searchKeyword,Pageable paging);
	
	
	//Page<T>
	//웹 페이지에서 페이징 처리에 필요한 다양한 정보를 한번에 얻을 수 있다.
	Page<Board> findByTitleContaining(String searchKeyword,Pageable paging);
	
	//@Query
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")//JPQL검색 대상은 영속성 컨텍스트
	List<Board> queryAnnotationTest1(String searchKeyword);//searchKeyword가 첫 번째 파라미터 값으로 바인딩된다.

	//JPQL에서는 사용자 입력 값을 바인딩할 수 있도록 위치 기반 파라미터와 이름 기반 파라미터  두 가지를 지원한다.
	//위치기반 ?1 이라고하면 첫 번쨰 파라미터를 의미한다.
	
	
	
	//이름기반 @Query    
	@Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	
	
	
	//엔티티를 통째로 검색하지 않고 특정 변수만 조회하기 @Query
	//검색 결과로 엔티티 객체가 조회되는 것이 아니라 여러 변수 값들이 조회된다 따라서 리턴타입을 List<Object[]>로 해야한다
	@Query("SELECT b.seq,b.title,b.writer FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Object[]>queryAnnotationTest3(@Param("searchKeyword") String searchKeyword);
	
	
	//@Query로 등록한 SQL은 프로젝트가 로딩되는 시점에 파싱되어 처리된다
	//따라서 SQL오류가 있으면 무조건 예외가 발생되고 프로그램이 실행되지 않는다./
	//프로그램이 실행되기 전에 사용할 SQL들을 모두 메모리에 올려둠으로써 성능을 향상시킬수 있기 때문이다.
	

	
	//네이티브 쿼리
	//@Query 사용하여 특정 데이터베이스에서만 사용되는 네이티브 쿼리 사용 가능
	//종속되는 문제가있지만 성능상 특정 데이터베이스에 최적화된 쿼리를 사용해야 하는 경우
	//from절에 엔티티가  아닌 정상적인 테이블 이름이 사용  select  where 절에서도
	@Query(value="select seq,title,writer from board where title like '%'||?1||'%' order by seq desc",nativeQuery = true)
	List<Object[]> queryAnnotationTest4(String searchKeyword);
	
	
	//@Query 페이징 및 정렬
	//매개변수로 Pageable 인터페이스 추가
	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest5(Pageable paging);
	
	
	
	

}
