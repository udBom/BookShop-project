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
@ToString(exclude = "order")
@Entity
public class OrderDetail {
	
	@Id
	@GeneratedValue
	private int oseq;	  // 주문번호	
	
	@ManyToOne
	@JoinColumn(name = "BOOK_ID", nullable = false, updatable = false)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID", nullable = false, updatable = false)
	private Order order;
	
	private int quantity = 1;
	
	
	public void setOrder(Order order) {
		this.order = order;
		order.getOdList().add(this);
	}
}
