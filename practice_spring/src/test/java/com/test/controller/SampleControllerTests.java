package com.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context-xml"})
@Log4j
public class SampleControllerTests {

	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mock;
	
	
	@Before
	public void setup() {
		this.mock = MockMvcBuilders.webAppContextSetup(ctx).build();
		
	}
	
	/*
	 * @Test
	 * public void testConvert() throws Exception {
	 * 
	 * Ticket ticket = new Ticket();
	 * ticket.setTno(123);
	 * ticket.setOwner("Admin");
	 * ticket.setGrade("AAA");
	 * 
	 * String jsonStr = new Gson().toJson(ticket);
	 * 
	 * log.info(jsonStr);
	 * 
	 * mock.perform(post("/sample/ticket")
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(jsonStr))
	 * .andExpect(status().is(200));
	 * }
	 */
	
	
}
