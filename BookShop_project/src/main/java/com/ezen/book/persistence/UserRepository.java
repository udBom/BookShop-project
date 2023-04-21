package com.ezen.book.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.book.domain.User;


public interface UserRepository extends JpaRepository<User,String>{
	@Query(value="SELECT u FROM users u", nativeQuery=true)
	Page<User> getUserList(Pageable page);

}
