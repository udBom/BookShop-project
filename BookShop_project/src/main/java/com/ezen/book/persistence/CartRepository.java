	package com.ezen.book.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezen.book.domain.Cart;


public interface CartRepository extends CrudRepository<Cart, Integer> {
	List<Cart> findByUser_id(String user_id);
	
	List<Cart> findByUser_idAndChecked(String user_id, Boolean checked);
}
