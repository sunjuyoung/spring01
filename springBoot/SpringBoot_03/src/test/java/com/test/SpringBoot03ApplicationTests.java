package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest //@SpringBootApplication 이 사용자가 작성한 빈과 자동설정 빈들을 모두 초기화하듯이
                //테스트 케이스가 실행될때 테스트에 필요한 모든 설정과 빈들을 자동으로 초기화하는 역활 수행
public class SpringBoot03ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
