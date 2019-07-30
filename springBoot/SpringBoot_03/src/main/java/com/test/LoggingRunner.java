package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class LoggingRunner implements ApplicationRunner{

	//slf4j 제공하는 LoggerFactory
	private Logger logger = LoggerFactory.getLogger(LoggingRunner.class);
	
	
	public void run(ApplicationArguments args) throws Exception{
		
		logger.trace("Trace 로그 메시지");
		logger.debug("DEBUG 메시지");
		logger.info("INFO 몌시지");
		logger.warn("WARN 메시지");
		logger.error("ERROR 메시지");
		
	}
}
