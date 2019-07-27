package com.test.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
public class pageDTO {
	
	private Criteria cri;

	//화면에 보여질 페이지버튼 수
	private int endPage, startPage;

	//게시물 총 수
	private int total;
	
	private boolean prev,next;
	
	public pageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		
		this.total = total;
		
		
		
		log.info("total :" + this.total);
		
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		
		log.info("endPage :" + endPage);

		this.startPage = endPage - 9;

		int realEnd = (int) (Math.ceil(this.total / 10.0));
		
		log.info("realEnd :" + realEnd);


		if (this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		 this.prev = cri.getPageNum()>1;
		 
		 this.next = cri.getPageNum() <= realEnd;
				 
		
		
		log.info("endpage :" + endPage);

	}
		
	
	

}
