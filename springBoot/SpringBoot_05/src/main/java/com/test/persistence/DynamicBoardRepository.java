package com.test.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.test.domain.Board;


//QuerydslPredicateExecutor 추가로 구현한다
//QuerydslPredicateExecutor 제공하는 메서드들은 공통적으로 Predicate 타입의 객체를 매개변수로 받고있다.
//Predicate 인터페이스를 구현한 클래스가 BooleanBuilder클래스이다
public interface DynamicBoardRepository extends CrudRepository<Board,Long>, QuerydslPredicateExecutor<Board>{
	
	
	
	

}
