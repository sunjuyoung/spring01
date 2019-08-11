package com.test.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

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
	
	//Like연산자 특정 단어가 포함된 글 목록 조회
	List<Board> findByContentContaining(String content);
	
	
	
	
	

}
