package com.ezen.book.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Search {

	private String searchCondition;
	private String searchKeyword;
}
