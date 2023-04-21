package com.ezen.book.service;

import java.util.List;

import com.ezen.book.domain.Cart;


public interface CartService {

	void insertCart(Cart vo);

	List<Cart> getCartList(String user_id);
	
	List<Cart> getCheckedCartList(String user_id);

	void deleteCart(int cseq);
	
	void updateCart(Cart cart);
	
	void checkDataCart(Cart cart);
	
	Cart findCartById(int cseq);
}
