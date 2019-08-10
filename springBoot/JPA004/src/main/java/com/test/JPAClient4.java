package com.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.test.domain.Board;

public class JPAClient4 {

	//엔티티 수정
	public static void main(String[] args) {
		
		
		/*
		 * 엔티티 수정에서 JPA기본전략은 모든 필드 수정이다 장점은 수정 쿼리가 항상 같기 때문에 애플리케이션 로딩 시점에 수정 쿼리를 미리 생성해
		 * 두고 재사용할수 있다는점이다 동일한 쿼리를 보내면 데이터베이스는 이전에 한 번 파싱했던 쿼리를 재사용 성능상 이점
		 */
		
		//persist() 엔티티 영속화 insert
		//merge() 준영속 상태의 엔티티를 영속화 update
		//remove() 영속상태의 엔티티를 제거 delete
		//find() 하나틔 엔티티를 검색한다 select one
		//createQuery() JPQL에 해당하는 엔티티 목록을 검색한다  selelct list
		

		
		//EntityManagerFactory 생성   (EntityManager생산하는 공장) 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA004");
		
		EntityManager em = emf.createEntityManager(); //CRUD 실질적으로 처리하기위한 객체 EntityMnanger
		
		EntityTransaction tx = em.getTransaction();
	
		try {
			tx.begin();

			//수정할 게시글 조회  -> 1차 캐시등록
			//수정하기 전에 find() 수정할 엔티티 검색
			//수정할 엔티티가 반드시 영속성 컨텍스트에 있어야한다 그렇지 않다면 예외 발생
			
			Board board = em.find(Board.class, 1L);
			
			//변수 값을 수정하면 JPA는 수정된 변수들을 찾아 UPDATE구문을 작성하여 SQL저장소에 저장
			
			board.setTitle("검색한 게시글의 제목 수정");

			
			
	
		
			
		tx.commit();  //실질적인 UPDATE가 처리
			
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			
		}finally {
			em.close();
			emf.close();
		}
		
		
	}

}
