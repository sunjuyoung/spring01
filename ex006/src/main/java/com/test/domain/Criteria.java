package com.test.domain;

import lombok.Data;

@Data

public class Criteria {

	private int pageNum;
	private int amount;

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	
	
	
}
