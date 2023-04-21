package com.ezen.book.service;

import org.springframework.data.domain.Page;

import com.ezen.book.domain.User;


public interface UserService {

	Page<User> getUserList(int page);
	User getUser(User user);
	void deleteUser(User user);
	void updateUser(User user);
	User findUserByid(String user_id);
	void updatePoint(User user);
}
