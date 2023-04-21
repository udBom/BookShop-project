package com.ezen.book.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@ToString(exclude="reviewList")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@Column(name="USER_ID")
	private String id;
	
	private String password ;//비밀번호
	private String name; //이름

	@Column(insertable = false)
	@ColumnDefault("0")
	private int		point = 0; //적립금
	
	@Enumerated(EnumType.STRING)
	private Role role; //관리자 이용자 구분

	@OneToMany(mappedBy="users", fetch=FetchType.EAGER)
	private List<Review> reviewList = new ArrayList<Review>();

}
