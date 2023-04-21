package com.ezen.book.persistence;

import org.springframework.data.repository.CrudRepository;

import com.ezen.book.domain.Book;
public interface BookRepository extends CrudRepository<Book, Integer> {
}
