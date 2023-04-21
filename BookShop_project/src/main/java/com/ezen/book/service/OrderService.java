package com.ezen.book.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ezen.book.domain.Order;


public interface OrderService {
	//고객
	/*
	 * 주문 테이블에서 다음에 저장할 주문번호 생성
	 */
	int selectMaxOseq();
	/*
	 * 주문 저장 후, 주문번호를 반환한다. -> order detail 저장시 사용
	 */
	void insertOrder(Order order);

	Order getOrderById(long oseq);
	
	List<Order> getListOrderById(String user_id);
	
	List<Order> listOrder(String mname);
	
	void updateOrderResult(long odseq, int result);
	
	
	
	//관리자
	Page<Order> getOrderList(int page);
	void updateOrder(Order order);
	Order getOrder(Order order);
	void deleteOrder(Order order);
}
