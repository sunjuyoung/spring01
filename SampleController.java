package com.test.sts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.SampleVO;
import com.test.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
//jsp와 달리 순수한 데이터를 반환하는 형태
//다양한 포맷읠 데이터를 전송 할 수 있습니다. 주로 문자열 JSON XML
//@contorller 는 문자열을 반환하는 경우 JSP파일의 일므으로 처리하지만
//restcontroller 의 경우 순수한 데이터가 됩니다 
	
	
	@GetMapping(value = "/getText" , produces = "text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
		
	}
	
	
	//produces 속성 생략가능 
	@GetMapping(value = "/getSample" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		
		log.info("MIME TYPE " + MediaType.TEXT_PLAIN_VALUE);
		
		return new SampleVO(112,"스타","로드");
		
	}
	
	
	//produces 속성 생략가능 , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE}
	@GetMapping(value = "/getSample2" )
	public SampleVO getSample2() {
		
		log.info("MIME TYPE " + MediaType.TEXT_PLAIN_VALUE);
		
		return new SampleVO(112,"로켓","라쿤");
		
	}
	
	//List 데이터 반환 
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){
		
		return IntStream.range(1,10).mapToObj(i -> new SampleVO(i,i+"first", i + " Last")).collect(Collectors.toList());
		
	}
	
	//Map
	@GetMapping(value = "/getMap")
	public Map<String,SampleVO> getMap(){
		
		Map<String,SampleVO> map = new HashMap<>();
		
		map.put("First", new SampleVO(111,"그루트","주니어"));
		
		return map;
	}
	
	
	//ResponseEntity
	//반드시 height weight 파라미터로 전달받는다 
	//height 150보다 작다면 502(bad gateway)상태 코드와 데이터를 전송한다
	@GetMapping(value= "/check" , params = {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		
		SampleVO vo = new SampleVO(0, ""+ height, ""+weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return  result;
	}
	
	
	//pathvariable
	//REST방식은 URL내에 최대한 많은 정보를 담는다
	// 예전에는 ?뒤 에 추가된느 쿼리 스트링 이라는 형태로 파라미터를 이용해서 전달되던 데이터들이
	//REST방식에서는 경로의 일부로 차용되는 경우가 많다
	//{}이용해서 변수명을 지정하고 지정된 이름의 변숫값을 얻을 수 있습니다 int double 같은 기본 자료형은 사용할 수 없다
	//sample/product/bags/1234 호출하면 cat pid변수의 값으로 처리
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		
		return new String[] {"category :" + cat , "productid " + pid};
	}
	
	
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("convert........ticket " +ticket);
		
		return ticket;
	}
	
	
}
