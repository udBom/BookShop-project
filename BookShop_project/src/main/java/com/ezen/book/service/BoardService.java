package com.ezen.book.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.book.domain.Book;



public interface BoardService {

	void insertBoard(Book board, MultipartFile file) throws Exception;
	void updateBoard(Book board);
	void deleteBoard(Book board);
	Book getBoard(Book board);
	Page<Book> getBoardList(int page);
}
