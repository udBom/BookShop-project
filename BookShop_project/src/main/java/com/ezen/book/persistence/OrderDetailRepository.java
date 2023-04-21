package com.ezen.book.persistence;

import org.springframework.data.repository.CrudRepository;

import com.ezen.book.domain.OrderDetail;


public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

}
