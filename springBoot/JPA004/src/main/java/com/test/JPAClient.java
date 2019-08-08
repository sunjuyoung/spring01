package com.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.test.domain.Board;

public class JPAClient {

	public static void main(String[] args) {

		
		//EntityManagerFactory 생성   (EntityManager생산하는 공장) 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA004");
		
		EntityManager em = emf.createEntityManager(); //CRUD 실질적으로 처리하기위한 객체 EntityMnanger
		
		//Transaction 생성
		EntityTransaction tx= em.getTransaction();
		
		try {
			
			tx.begin();
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setCnt(0L);
			board.setContent("JPA 내용");
			board.setWriter("글쓴이");
			board.setCreateDate(new Date());
			
			em.persist(board); //Board엔티티에 설정된 값을 BOARD테이블에 저장한다.
			
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
