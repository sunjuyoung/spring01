package com.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.test.domain.Board;

public class JPAClient2 {

	public static void main(String[] args) {

		
		//EntityManagerFactory 생성   (EntityManager생산하는 공장) 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA004");
		
		EntityManager em = emf.createEntityManager(); //CRUD 실질적으로 처리하기위한 객체 EntityMnanger
		
	
		try {
			
			//글 상세 조회
			Board searchBoard = em.find(Board.class,1L);  
			System.out.println("---------->" + searchBoard.toString());
			
	
		
			
		
			
		}catch(Exception e) {
			e.printStackTrace();
			
			
		}finally {
			em.close();
			emf.close();
		}
		
		
	}

}
