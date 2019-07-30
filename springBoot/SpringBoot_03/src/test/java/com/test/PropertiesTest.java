package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//복잡한 테스트  설정들을 자동으로 처리하고 테스트 관련 객체들도 메모리에 올린다
//속성 properties,classes,webEnvironment
//외부 프로퍼티를 재정의할수도 있고 새로운 프로퍼티를 추가할수도있다.
@SpringBootTest (properties = {"author.name=HELLO",
						 		"author.age=33",
						 		 "author.nation=Korea"})
public class PropertiesTest {

	
	@Autowired
	Environment env;
	
	@Test
	public void testMethod() {
		
		//외부 프로퍼티 정보 출력
		System.out.println("이름 : " + env.getProperty("author.name"));
		System.out.println("나이 : " + env.getProperty("author.age"));
		System.out.println("국가 : " + env.getProperty("author.nation"));
		
		
		
		
	}
	
}
