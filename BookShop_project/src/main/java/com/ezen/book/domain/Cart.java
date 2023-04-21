package com.ezen.book.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
public class Cart {
	@Id
	@GeneratedValue
	private int cseq;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "BOOK_ID", nullable = false, updatable = false)
	private Book book;
	
	private int quantity = 1;
	private Boolean checked = true;
	
	public static Cart createCart(User user, Book book) {
		Cart cart = new Cart();
		cart.user = user;
		cart.book = book;
		return cart;
	}
}
