package com.test.persistence;

import org.springframework.data.repository.CrudRepository;

import com.test.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String>{
	

}
