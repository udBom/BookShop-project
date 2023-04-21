package com.ezen.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.book.domain.Book;
import com.ezen.book.domain.Search;
import com.ezen.book.persistence.BookRepository;
import com.ezen.book.persistence.QueryDslBookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	
	private final QueryDslBookRepository QueryDslBookRepo;
	private final BookRepository bookRepo;
	
	//이용자
	// 전체 조회
	@Override
	public List<Book> getBookList(String bookKind, Integer bookType) {
		
		String type = null;
		List<Book> bookList = null;
		
		if(bookType != null) {
			if(bookType == 1) {
				type = "국내";
			} else if(bookType == 2) {
				type = "해외";
			}
		}
		
		if(bookKind.equals("best")) {
			bookList = QueryDslBookRepo.getBestBookList(type);
		} else if(bookKind.equals("new")) {
			bookList = QueryDslBookRepo.getNewBookList(type);
		}
		
		return bookList;
	}
	
	// 책 상세정보
	@Override
	public Book getBook(Book book) {
		return bookRepo.findById(book.getId()).get();
	}
	
	@Override
	public List<Book> getSearchList(Search search) {
		
		if (search.getSearchCondition() == null)
			search.setSearchCondition("all");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		
		return QueryDslBookRepo.getSearchList(search);
	}
	
}
