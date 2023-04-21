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
import lombok.experimental.SuperBuilder;
@Entity
@Setter
@ToString
@DynamicInsert 
@NoArgsConstructor
@SuperBuilder @Getter
public class Reply {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reseq;
	
	
	private String recontent;//답변


	@ManyToOne
	@JoinColumn(name="seq",updatable=false)
	public Qna qna; 
	
	public void setQna(Qna qna) {
		this.qna = qna;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date regdate = new Date(); //답변 날짜
	
	public void regDate(Date date) {
		regdate = new Date();
	}
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
    private User users; // 작성자
	
	public void setUser(User users) {
		this.users = users;
	}
}
