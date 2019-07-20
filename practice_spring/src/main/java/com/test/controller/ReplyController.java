package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.ReplyVO;
import com.test.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/reply")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {

	
	private ReplyService service;
	
	
	@GetMapping(value = "/get/{bno}",
			produces= {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Long bno){
		
	
		log.info("controller!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+bno);

	
		
		//Criteria cri = new Criteria(1,10);

		return new ResponseEntity<>(service.getListWithPaging(bno),HttpStatus.OK);
		
		
	}

	
	

}
