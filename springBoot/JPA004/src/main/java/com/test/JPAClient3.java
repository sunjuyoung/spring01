package com.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.test.domain.Board;

public class JPAClient3 {

	public static void main(String[] args) {
		
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
			
			Board board = new Board();
			board.setTitle("JPA SEQ_GENERATOR");
			board.setCnt(0L);
			board.setContent("JPA SEQ_GENERATOR");
			board.setWriter("SEQ_GENERATOR");
			board.setCreateDate(new Date());
			
			em.persist(board);
			
	
		
			
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
