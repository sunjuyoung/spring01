package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.pageDTO;
import com.test.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/reply/*")
public class ReController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model,Criteria cri) {
		

		int total = service.totalCount(cri);


		model.addAttribute("page", new pageDTO(cri,total));
		model.addAttribute("list", service.getListWithPaging(cri));
	
	}
	
	
	@GetMapping("/get")
	public void getContent(Criteria cri, BoardVO vo,Model model) {

		
		
		model.addAttribute("board",service.get(vo));
		
	}
	
	
}
