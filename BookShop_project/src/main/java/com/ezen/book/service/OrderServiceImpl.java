package com.ezen.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezen.book.domain.Order;
import com.ezen.book.persistence.OrderRepository;



@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepo;

	
//고객

	@Override
	public int selectMaxOseq() {
		return orderRepo.MaxOseq();
	}

	@Override
	public void insertOrder(Order order) {
		orderRepo.save(order);
	}

	@Override
	public List<Order> getListOrderById(String user_id) {
		return orderRepo.findByUser_idOrderByIndateDesc(user_id);
	}

	public List<Order> listOrder(String mname) {
		return (List<Order>) orderRepo.findAll();
	}


	@Override
	public void updateOrderResult(long oseq, int result) {
		Order findOrder = orderRepo.findById(oseq).get();
		findOrder.setResult(result);
		orderRepo.save(findOrder);
	}

	@Override
	public Order getOrderById(long oseq) {
		return orderRepo.findById(oseq).get();
	}

	
	
	
	
	

	
	
//관리자
	@Override
	public Page<Order> getOrderList(int page) {
		
		return orderRepo.findAll(PageRequest.of(page, 10, Sort.Direction.DESC,"oseq"));	
	}


	@Override
	public void updateOrder(Order order) {

		Order findOrder = orderRepo.findById(order.getOseq()).get();
		findOrder.setUser(order.getUser());
		findOrder.setResult(order.getResult());	
		findOrder.setDelivery_fee(order.getDelivery_fee());
		findOrder.setTotal_price(order.getTotal_price());
		findOrder.setUsed_point(order.getUsed_point());
		orderRepo.save(findOrder);
	}


	@Override
	public Order getOrder(Order order) {
		
		return orderRepo.findById(order.getOseq()).get();
	}


	@Override
	public void deleteOrder(Order order) {
	orderRepo.deleteById(order.getOseq());
	}

	
}
