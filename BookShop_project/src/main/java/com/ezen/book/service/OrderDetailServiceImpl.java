package com.ezen.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.book.domain.OrderDetail;
import com.ezen.book.persistence.OrderDetailRepository;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository odRepo;

	@Override
	public void insertOrderDetail(OrderDetail order) {
		odRepo.save(order);
	}

}
