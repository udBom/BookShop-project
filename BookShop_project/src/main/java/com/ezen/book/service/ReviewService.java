package com.ezen.book.service;

import org.springframework.data.domain.Page;

import com.ezen.book.domain.Review;


public interface ReviewService {
	
	Page<Review> getReviewList(int page); //페이징 처리가 된 리뷰 목록! page를 변수로 칭해줘야 다음 페이지의 화면이 출력된다.
	
	Review getReview(Review review); //리뷰 상세 보기 페이지
	
	void insertReview(Review review);

	void updateReview(Review review);
	
	void deleteReview(Review review);
	
}
