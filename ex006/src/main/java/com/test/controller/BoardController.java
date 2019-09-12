package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.BoardVO;
import com.test.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	
	//메인
	@GetMapping("/main")
	public void main() {
		
	}
	
	
	//리스트
	@GetMapping("/list")
	public void boardList(Model model) {
		
		log.info("list controller");
		
		model.addAttribute("list",service.getList());
		
		
	}
	
	
	
	//
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	//등록
	@PostMapping("/register")
	public String register(BoardVO vo,RedirectAttributes rttr) {
		
		log.info("register controller");
		
		int result = service.insert(vo);
		
		rttr.addFlashAttribute("result", result);//flashAttribute 일회성으로 데이터를 전달한다
		
		return "redirect:/board/list";
	}
	
	
	//조회
	@GetMapping({"/get","/modify"})
	public void get(Long bno,Model model) {//@RequsetParam 을 이용해서 명시적으로 처리할수있다
		
		log.info("get controller");
		
		BoardVO board = service.read(bno);
		
		model.addAttribute("board" , board);
		
	}
	
	//수정
	@PostMapping("/modify")
	public String modify(BoardVO vo , RedirectAttributes rttr) {
		
		
		if(service.update(vo) == 1) {
			rttr.addAttribute("result","success");
		}
		
		return "redirect:/board/list";
	}
	
	
	//삭제
	@PostMapping("/remove")
	public String remove(Long bno,RedirectAttributes rttr) {
		
		service.remove(bno);
		
		return "redirect:/board/list";
	}
	
	

}
