package com.test.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) 
										  throws IOException, ServletException {
		
		log.warn("Login success");
		List<String> roleNames = new ArrayList<>();
		
		
		
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
			
		});
		
	
		
		log.warn("ROLE NAMES : "+ roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
	
			request.setAttribute("ID",auth.getName());
		
			response.sendRedirect("/reply/list");
			request.setAttribute("ID",auth.getName());
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("/reply/list");
			return;
		}
		
		
		response.sendRedirect("/");
	}

}
