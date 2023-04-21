package com.ezen.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezen.book.domain.User;
import com.ezen.book.persistence.UserRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public Page<User> getUserList(int page) {
		return userRepo.findAll(PageRequest.of(page, 10,Sort.Direction.DESC,"name"));
	}

	@Override
	public User getUser(User user) {
		
		return userRepo.findById(user.getId()).get();
	}

	@Override
	public void deleteUser(User user) {
		userRepo.deleteById(user.getId());
	}

	@Override
	public void updateUser(User user) {
		User findUser = userRepo.findById(user.getId()).get();
		
		findUser.setName(user.getName());
		findUser.setPassword(user.getPassword());
		findUser.setPoint(user.getPoint());
		findUser.setRole(user.getRole());
		
		userRepo.save(user);
		}
	
	@Override
	public User findUserByid(String user_id) {
		return userRepo.findById(user_id).get();
	}

	@Override
	public void updatePoint(User user) {
		User tuser = userRepo.findById(user.getId()).get();
		tuser.setPoint(user.getPoint());
		
		userRepo.save(tuser);
	}
}
