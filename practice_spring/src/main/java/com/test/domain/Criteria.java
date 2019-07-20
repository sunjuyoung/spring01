package com.test.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
public class Criteria {

	//한페이지에 나타낼 게시물 수
	private int amount; //10

	//선택한 페이지
	private int pageNum; //1


	
	private String type,keyword; //검색조건

	  public Criteria() {
		    this(5, 1);
		  }
	
	
	public Criteria(int amount, int pageNum) {
		
		this.amount = amount;
		
		this.pageNum = pageNum;



}
}
