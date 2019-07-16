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

	//화면에 보여질 페이지버튼 수
	private int endPage, startPage;

	//게시물 총 수
	private int total;
	
	private String type,keyword; //검색조건

	public Criteria(){};
	
	
	public Criteria(int amount, int pageNum) {
		
		
		
		this.amount = amount;
		
		this.pageNum = pageNum;
		

		log.info("@@@@@@@total@ :" + this.total);
		
		this.endPage = (int) (Math.ceil(pageNum / 10.0)) * 10;
		
		log.info("@@@@@@@@@@@@@@@@ :" + endPage);

		this.startPage = endPage - 9;

		int realEnd = (int) (Math.ceil(this.total / 10.0));
		
		log.info("@@@@@@@@@@@@@@@@ :" + realEnd);


		if (this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		log.info("endpage :" + endPage);

	}



}
