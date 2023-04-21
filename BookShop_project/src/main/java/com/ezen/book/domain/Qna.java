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

import org.hibernate.annotations.DynamicInsert;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@Getter
public class Qna {
	
	@Id
	@Column(updatable=false,name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long seq; //qna 테이블 id
	
	private String title; //제목
	
	private String recontent; //답글
	
	private String content; //질문 내용
	
	@Column(updatable=false)
	private String genre;
	


	@ManyToOne
	@JoinColumn(name="USER_ID", updatable=false)
	public User users;
	
	public void setUser(User user) {
		this.users=user;
	}
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date regdate = new Date();
	
	
	

}
