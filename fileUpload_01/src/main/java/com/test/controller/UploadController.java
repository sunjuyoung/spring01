package com.test.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	//기본 페이지
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
		log.info("Upload form--------------------------------------");
		
	}

	//파일 저장
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		//저장위치
		String uploadFolder = "C:\\upload"; 
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("--------------------------");
			log.info("upload File name : " + multipartFile.getOriginalFilename());
			log.info("Upload file size : "+ multipartFile.getSize());
			
		
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);  //transferTo()의 파라미터로는 java.io.File 객체를 지정하면 됩니다
			}catch(Exception e) {
				log.error(e.getMessage());
			}
			
		}
		
	}
	

	//ajax 기본 페이지
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
		log.info("Upload ajax..................................");
	}
	
	
	
	//Ajax 방식으로 파일 저장
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		
		
		log.info("Upload ajax 2..................................");
		
		//저장위치
		String uploadFolder = "C:\\upload"; 
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("--------------------------");
			log.info("upload File name : " + multipartFile.getOriginalFilename());
			log.info("Upload file size : "+ multipartFile.getSize());
			
		
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);  //IE 경우 전체파일경로가 전송되므로 마지막 \\ 기준으로 잘라낸다
			
			log.info("only file name ::::" + uploadFileName);
			
			File saveFile = new File(uploadFolder, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);  //transferTo()의 파라미터로는 java.io.File 객체를 지정하면 됩니다
			}catch(Exception e) {
				log.error(e.getMessage());
			}
			
		}
		
	}
	
	
}
