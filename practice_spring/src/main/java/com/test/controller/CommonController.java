package com.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {

	
	//에러 페이지
	@GetMapping("/accessError")
	public void accessError(Authentication auth, Model model) {
		
		
		model.addAttribute("msg","Access Denied");
	}
	
	//로그인 페이지
	@GetMapping("/customLogin")
	public void loginInput(String error,String logout, Model model) {
		
		if(error != null) {
			model.addAttribute("error","Login Error Check your Account");
		}
		
		if(logout != null) {
			model.addAttribute("logout","로그아웃 완료");
		}
		
	}
	
	
}
