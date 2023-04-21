package com.ezen.book.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.book.domain.Book;

public interface BoardRepository extends JpaRepository<Book, Integer>{
	@Query(value="SELECT b FROM Board b", nativeQuery =true)
	Page<Book> getBoardList(Pageable page);	
	
	
	
}

