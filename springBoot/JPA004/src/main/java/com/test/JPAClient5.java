package com.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.test.domain.Board;

public class JPAClient5 {

	//엔티티 삭제
	public static void main(String[] args) {
		

		//일차적으로 영속성 컨텍스트에서 해당 엔티티가 빠지고 delete구문이 SQL저장소에 등록
		
		
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

			//삭제할 게시글 조회  -> 1차 캐시등록
			//삭제하기 전에 find() 수정할 엔티티 검색
			//삭제할 엔티티가 반드시 영속성 컨텍스트에 있어야한다 그렇지 않다면 예외 발생
			
			Board board = em.find(Board.class, 1L);
			board.setSeq(1L);

			
			//게시글 삭제
			board.setSeq(1L);
			em.remove(board);
			

			
		tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			
		}finally {
			em.close();
			emf.close();
		}
		
		
	}

}
