package com.ezen.book.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "odList")
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@Column(name = "ORDER_ID")
	private long oseq;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, updatable = false)
	private User user;
	
	@OneToMany(mappedBy="order", fetch=FetchType.LAZY)
	private List<OrderDetail> odList = new ArrayList<OrderDetail>();
	
	private int total_price;
	private int used_point;
	private int delivery_fee;
	
	private int result = 1;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date indate = new Date();
}
