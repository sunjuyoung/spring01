package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.pageDTO;
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


		model.addAttribute("page", new pageDTO(cri,total));
		model.addAttribute("list", service.getListWithPaging(cri));
	
	}
	
	
	@GetMapping("/get")
	@PreAuthorize("isAuthenticated()")
	public void getContent(Criteria cri, BoardVO vo,Model model) {

		
		
		model.addAttribute("board",service.get(vo));
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/insertPage")
	public void insertPage(Criteria cri) {
		
		log.info("insertPage~~~~~~");
		
		
	}
	
	//시큐리티를 사용할떄 post방식은 반드시 CSRF토큰을 사용하도록 추가해야합니다.
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/insertPage")
	public String insertPage(BoardVO vo, RedirectAttributes rttr) {
		
		log.info("insert~~~~~~" + vo);
		
		int success = service.insert(vo);
		
		log.info("success : " + success);
		
		rttr.addAttribute("success",success);
		
		return "redirect:/reply/list";
		
		
	}

	
	@PostMapping("/delete")
	public void delete(BoardVO vo) {
		
		
		
		
	}
	
	@PostMapping("/modify")
	public void modify(BoardVO vo) {
		
		
		
		
	}
	

	
	@GetMapping("/gallery")
	public void gallery(BoardVO vo) {
		
		
		
		
	}
	
	
	
	
}
