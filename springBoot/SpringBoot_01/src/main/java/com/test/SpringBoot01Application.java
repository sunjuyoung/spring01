package com.test;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//componentscan xml설정없이 처리되고있다  @SpringBootApplication ctrl 눌러 확인해 보면 
//component 애노테이션 확인 com.test를 설정한것과 동일한 동작한다 
//다른 패키지 생성 후  클래스 작동하면 해당 클래스는 빈으로 등록되지 않게된다
@SpringBootApplication
@ComponentScan(basePackages = {"com.sun","com.test"}) //다른 패키지 지정하고 싶으면 기존 패키지와 포함 직접 지정해야 한다
public class SpringBoot01Application {

	public static void main(String[] args) {
		
		//웹  어플리케이션이 아닌 일반 자바 애플리케이션으로 실행하고자 할때 , 톰캣 서버도 구동되지 않는다
		
		SpringApplication application = new SpringApplication(SpringBoot01Application.class);
		
		application.setWebApplicationType(WebApplicationType.SERVLET); //타입을 none, 웹 어플리케이션을 실행할려면 타입을 SERVLET으로 변경
		
		//application.setBannerMode(Banner.Mode.OFF); //배너 모드 OFF
		
		application.run(args);
		
		//SpringApplication.run(SpringBoot01Application.class, args);
		
		
		
		//더 간단한 방법은 application.properties 파일에서 설정가능 , properties 설정이 우선순위가 더 높다.
		
	}

}
