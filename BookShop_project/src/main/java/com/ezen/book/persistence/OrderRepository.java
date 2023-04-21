package com.ezen.book.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.book.domain.Order;
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value="SELECT o FROM orders o", nativeQuery=true)
	Page<Order> getOrderList(Pageable page);
	
	
	
	@Query(value = "SELECT NVL2(MAX(o.ORDER_ID), MAX(o.ORDER_ID)+1, 1) FROM orders o", nativeQuery = true)
	int MaxOseq();
	
	List<Order> 	findByUser_idOrderByIndateDesc(String user_id);

}
