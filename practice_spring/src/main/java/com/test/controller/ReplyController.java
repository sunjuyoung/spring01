package com.test.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
				produces = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Long bno) {

		//log.info("controller!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + bno);

		return new ResponseEntity<>(service.getListWithPaging(bno), HttpStatus.OK);

	}

	//json방식의 데이터만 처리하고, 문자열을 반환한다.
	//	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/new",
				consumes = "application/json", produces = {
				MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {

		log.info(vo);

		return service.insert(vo) == 1 ? new ResponseEntity<>("success",HttpStatus.OK)
										: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//댓글 삭제
	@DeleteMapping(value = "/{rno}",
				produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("reply remove");

		return service.delete(rno) == 1 ? new ResponseEntity<>("success",HttpStatus.OK)
										: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//댓글 수정
	@PreAuthorize("principal.username == #vo.replyer")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
				value = "/{rno}", 
				consumes = "application/json",
				produces = {MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> update(@RequestBody ReplyVO vo,
											@PathVariable("rno") Long rno) {

		log.info("reply update");
		vo.setRno(rno);

		return service.modify(vo) == 1 ? new ResponseEntity<>("success",HttpStatus.OK)
										: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
