package com.ezen.book.service;

import java.util.List;


import com.ezen.book.domain.Book;
import com.ezen.book.domain.Search;

public interface BookService {
	
	//이용자

	// 전체 조회
	List<Book> getBookList(String bookKind, Integer bookType);
	
	//상세정보
	Book getBook(Book book);
	
	//검색
	List<Book> getSearchList(Search search);
	
}
