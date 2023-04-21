package com.ezen.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.book.domain.Cart;
import com.ezen.book.persistence.CartRepository;


@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public void insertCart(Cart vo) {
		cartRepo.save(vo);
	}

	@Override
	public List<Cart> getCartList(String user_id) {
		return cartRepo.findByUser_id(user_id);
	}
	
	@Override
	public List<Cart> getCheckedCartList(String user_id) {
		return cartRepo.findByUser_idAndChecked(user_id, true);
	}

	@Override
	public void deleteCart(int cseq) {
		cartRepo.deleteById(cseq);
	}

	@Override
	public void updateCart(Cart cart) {
		Cart findCart = cartRepo.findById(cart.getCseq()).get();
		findCart.setQuantity(cart.getQuantity());
		
		cartRepo.save(findCart);
	}

	@Override
	public Cart findCartById(int cseq) {
		return cartRepo.findById(cseq).get();
	}

	@Override
	public void checkDataCart(Cart cart) {
		Cart findCart = cartRepo.findById(cart.getCseq()).get();
		findCart.setChecked(cart.getChecked());
		
		cartRepo.save(findCart);
	}

}
