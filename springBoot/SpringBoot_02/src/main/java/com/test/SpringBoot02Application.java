package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//메인클래스
//내장 톰캣 구동

//@SpringBootApplication
@SpringBootConfiguration  //@configuration 과 동일하다 ,환경설정 빈 클래스를 표현할때 사용
@EnableAutoConfiguration
@ComponentScan //객체를 빈으로 등록 ,메모리에 올리는 역활 @Configuration,@Repository,@Service @Controller @RestController 

public class SpringBoot02Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02Application.class, args);
	}

}
