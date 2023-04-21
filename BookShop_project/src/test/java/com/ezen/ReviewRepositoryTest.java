package com.ezen;


import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ezen.book.domain.Review;
import com.ezen.book.domain.Role;
import com.ezen.book.domain.User;
import com.ezen.book.persistence.ReviewRepositoy;
import com.ezen.book.persistence.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReviewRepositoy reviewRepo;

	//Best review test
	@Disabled 
	@Test
	public void testInsert() {
		User user1 = new User();
		user1.setId("doolly123");
		user1.setPassword("doolly123");
		user1.setName("둘리");
		user1.setRole(Role.ROLE_USER);
		//member1.setPoint(0);
		userRepo.save(user1);
		
		User user2 = new User();
		user2.setId("downer456");
		user2.setPassword("downer456");
		user2.setName("도우너");
		user2.setRole(Role.ROLE_USER);
		//member2.setPoint(0);
		userRepo.save(user2);
		
		for (int i = 1; i <= 3; i++) {
			Review review = new Review();
			review.setUsers(user1);
			review.setBook_id("궁금한 이야기 why "+i );
			review.setContent(user1.getId() + "님이 "+i+"번째 등록한 리뷰 입니다.");
			review.setRegdate(new Date());
			reviewRepo.save(review);
		}
		
		for (int i = 1; i <= 3; i++) {
			Review review = new Review();
			review.setUsers(user2);
			review.setBook_id("해리포터 "+i);
			review.setContent(user2.getId() + "님이 " + i +"번째 등록한 리뷰입니다.");
			review.setRegdate(new Date());
			reviewRepo.save(review);
		}
	}
	
	

}
