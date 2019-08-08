package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/reply")
public class EtcController {

	@GetMapping("/gallery")
	public void gallery(String code) {

		log.info("gallery form");

	}
	
	

	@GetMapping("/books")
	public void books() {

	}

	//파일 업로드 처리
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile) {

		String uploadFolder = "C:\\upload";

		for (MultipartFile multipartFile : uploadFile) {

			log.info("upload file name : " + multipartFile.getOriginalFilename()); //IE경우 전체 경로 까지 출력
			log.info("upload file name : " + multipartFile.getName()); //input 태그 네임

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);

			} catch (Exception e) {

			}

		}

	}

	//파일 업로드 Ajax
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String uploadAjaxAction(MultipartFile[] uploadFile) {

		log.info("uploadAjaxAction");

		String uploadFolder = "C:\\upload";

		for (MultipartFile multipartFile : uploadFile) {
			log.info("-------------------------------------");

			String uploadFileName = multipartFile.getOriginalFilename();

			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1); //IE 위한 경로 제거

			log.info(uploadFileName);

			File saveFile = new File(uploadFolder, uploadFileName);

			try {

				multipartFile.transferTo(saveFile);

			} catch (Exception e) {
				log.error(e.getMessage());
			}

		}
		return "redirect:/reply/gallery";

	}

	@RequestMapping("/book_info")
	public @ResponseBody String bookInfo() {

		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, String> params = new HashMap<>();

		params.put("title", "자바");

		String body = null;
		try {
			body = objectMapper.writeValueAsString(params);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

	//	HttpEntity entity = new HttpEntity(body, headers);

		String result= restTemplate.getForObject(
		"http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=ttbsyseoz1647001&Query=aladdin&QueryType=Title&title={title}&MaxResults=10&start=1&SearchTarget=Book&output=JS&Version=20131101",
		String.class,params);


		

		return result;
	}

}
