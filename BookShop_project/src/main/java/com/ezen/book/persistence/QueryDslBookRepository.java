package com.ezen.book.persistence;

import java.util.List;

import com.ezen.book.domain.Book;
import com.ezen.book.domain.Search;

public interface QueryDslBookRepository {
	
	List<Book> getBestBookList(String type);
	
	List<Book> getNewBookList(String type);
	
	List<Book> getSearchList(Search search);
	
}
