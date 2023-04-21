package com.ezen.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezen.book.domain.Book;
import com.ezen.book.domain.Search;
import com.ezen.book.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BookController {
	
	private final BookService bookService;
	
	@GetMapping("/main2")
	public String main2() {
		return "main2";
	}
	
	@GetMapping("/main")
	public String main(Model model, Book book) {
		String best = "best";
		String newBook = "new";
		Integer bookType = null;
		
		List<Book> bestBookList = bookService.getBookList(best, bookType);
		
		List<Book> newBookList = bookService.getBookList(newBook, bookType);
		
		model.addAttribute("bestBookList", bestBookList);
		model.addAttribute("newBookList", newBookList);
		
		return "main";
	}
	
	// best,new , 국내,해외 분류 전체 조회
	@GetMapping("/getBookList")
	public String getBookList(Model model, HttpServletRequest request, Book book) {
		
		Integer bookType = null;
		
		String bookKind = request.getParameter("kind");
		String type = request.getParameter("type");
		
		if(type != null) {
			bookType = Integer.valueOf(type);
		}
		
		List<Book> bookList = bookService.getBookList(bookKind, bookType);
		
		model.addAttribute("bookList",bookList);
		
		return "getBookList";
	}
	
	//상세정보
	@GetMapping("/getBook")
	public String getBook(Book book, Model model) {
		
		model.addAttribute("book", bookService.getBook(book));
		
		return "getBook";
	}
	
	//검색
	@GetMapping("/getSearchList")
	public String getSearchList(Search search, Model model) {
		
		List<Book> searchList = bookService.getSearchList(search);
		
		model.addAttribute("searchList", searchList);
		
		return "getSearchList";
	}
	
}
