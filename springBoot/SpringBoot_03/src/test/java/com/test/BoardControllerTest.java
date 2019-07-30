package com.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.test.service.BoardService;
//MebMvcTest를 사용하여 서블릿 컨테이너를 모킹하는 실습
@RunWith(SpringRunner.class)
//@WebMvcTest //@controller restController가 설정된 클래스들을 찾아 메모리에 생성한다  @SpringBootTest와 같이 사용 불가 서로MockMvc모킹하기떄문에 충돌
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc//@service 나 @Repository가 붙은 객체들도 모두 메모리에 올린다 즉 간단히 테스트하기 위해서는 WebMvcTest
public class BoardControllerTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private BoardService service;
	
	@Test
	public void testHello() throws Exception{
		
		//아직 비즈닉스 컴포넌트까지 완성되지않아 인터페이스만 제공되는 경우
		//
		when(service.hello("둘리")).thenReturn("Hello:둘리");
		
		//perfom() , 마치 브라우저에서 서버에 url요청하듯 컨트롤러 실행할 수 있다
		//andExpect(), 서버의 응답 결과도 검증
		//param() 키,값의 파라미터를 여러 개 전달할 수있다
		//status() 응답 상태 코드를 검증할 수 있다. isOk() isNotFound() isMethodNotAllowed() isInteralServerError()
		//컨트롤러가 리턴하는 뷰를 검증할때에는 view() 사용  , andExpect(view().name("/hello"))
		//요청 처리 결과가 리다이렉트 응답이라면 redirectedUrl() 사용   , andExpect(redirectedUrl("/index"))  = "/index" 화면으로 리다이렉트했는지 검증
		mock.perform(get("/hello").param("name","둘리"))
		.andExpect(status().isOk())
		.andExpect(content().string("Hello:둘리"))
		.andDo(print());
		
		
	}
}
