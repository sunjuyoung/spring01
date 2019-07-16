package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.domain.Criteria;
import com.test.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/reply")
public class ReController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model,Criteria cri) {


		
		int total = service.totalCount(cri);

		cri.setTotal(total);
		

		model.addAttribute("page", new Criteria(cri.getAmount() <=0? cri.getAmount():5,cri.getPageNum()));
		model.addAttribute("list", service.getListWithPaging(cri));
		
	
		
	}
	
	
	
	
}
