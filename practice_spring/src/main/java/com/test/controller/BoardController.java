package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.pageDTO;
import com.test.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/reply")
public class BoardController {

	@Autowired
	private BoardService service;
	
	
	//게시판
	@GetMapping("/list")
	public void list(Model model,Criteria cri) {
		

		int total = service.totalCount(cri);


		model.addAttribute("page", new pageDTO(cri,total));
		model.addAttribute("list", service.getListWithPaging(cri));
	
	}
	
	//게시물 선택 및 수정
	@GetMapping({"/get","/modify"})
	@PreAuthorize("isAuthenticated()")
	public void getContent(Criteria cri, BoardVO vo,Model model) {

		
		
		model.addAttribute("board",service.get(vo));
		
	}
	
	//글 작성페이지
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/insertPage")
	public void insertPage(Criteria cri) {
		
		log.info("insertPage~~~~~~");
		
		
	}
	
	//글 작성 전송
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

	//글 삭제
	@PostMapping("/delete")
	public String delete(@RequestParam("bno")Long bno, RedirectAttributes rttr) {
		
		log.info(bno);
		
		int result = service.remove(bno);
		
		log.info("delete : " +result);
		
		rttr.addAttribute("result",result);
		
		return "redirect:/reply/list";
	}
	
	//글 수정
	@PostMapping("/modify")
	public String modify(BoardVO vo, RedirectAttributes rttr) {
		
		int result = service.update(vo);
		
		rttr.addAttribute("result",result);
		
		return "redirect:/reply/get";
		
	}
	

	
	
	
	
	
}
