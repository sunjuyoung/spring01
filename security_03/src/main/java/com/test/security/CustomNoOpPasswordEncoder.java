package com.test.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

//4버전까지는 NoOpPasswordEncoder를 이용해서 처리해왔지만
//5부터는 deprecated되어서 더이상 사용할수가 없다
@Log4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		log.warn("before encode : " + rawPassword);
		
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		log.warn("matches : " + rawPassword + ":" + encodedPassword);
		
		return rawPassword.toString().equals(encodedPassword);
	}
	
	

}
