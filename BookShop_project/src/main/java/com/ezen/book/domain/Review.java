package com.ezen.book.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long seq;
	
	@Column(updatable=false)
	private String book_id;
	
	private String content;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date regdate = new Date();
	
	@ManyToOne
	@JoinColumn(name="USER_ID",nullable=false, updatable=false) //nullable=false는 joinColumn에 필요!
	private User users;
	
	
	@Column(updatable=false)
	private Long cnt=0L;


}
