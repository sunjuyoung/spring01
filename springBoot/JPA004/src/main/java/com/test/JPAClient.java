package com.test;

import java.util.Date;
import java.util.List;

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
			board.setTitle("JPA 제목4444");
			board.setCnt(0L);
			board.setContent("JPA 내용4444");
			board.setWriter("글쓴이");
			board.setCreateDate(new Date());
			
			em.persist(board); //Board엔티티에 설정된 값을 BOARD테이블에 저장한다.
			
			
			tx.commit();
			
			
			//목록을 조회하기 위해서는 JPQL 명령어 사용
			
			String jpql = "select b from Board b order by b.seq desc";
			
			//createQuery() 이용하여 작성된 JPQL을 전송한다 sql문과 거의 유사하다
			//검색 대상이 테이블이  아니라 엔티티라는 점 때문에 엔티티이름과 엔티티가 가지고있는 변수를 이요하여 쿼리를 구성해야 한다
			//JPQL검색 기능을 수행하면 쿼리를 실행하기전에 SQL저장소에 저장되어있던 모든 SQL구문들을 데이터베이스에 전손한다
			List<Board> boardlist = em.createQuery(jpql,Board.class).getResultList();
			for(Board brd : boardlist) {
				System.out.println("---->" + brd.toString());
			}
			
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
