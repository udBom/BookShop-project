package com.ezen.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezen.book.domain.Review;
import com.ezen.book.persistence.ReviewRepositoy;


@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepositoy reviewRepo;
	
	@Override
	public Page<Review> getReviewList(int page) { //0~5 리뷰 페이지 조회 
		
		Pageable pageable = PageRequest.of(page, 5,Sort.by(Sort.Direction.DESC,"seq")); //page라는 변수를 사용하여 다음 페이지로 넘어가도록 int page 생성
		
		return reviewRepo.findAll(pageable);
	}
	
	
	@Override  //리뷰 상세 보기 페이지
	public Review getReview(Review review) {
		return reviewRepo.findById(review.getSeq()).get();
	}

	//review 등록 하기 폼 페이지
	@Override
	public void insertReview(Review review) {
		
		reviewRepo.save(review);

	}
	
	//review 수정 페이지 
	@Override
	public void updateReview(Review review) {
		
		Review origReview = reviewRepo.findById(review.getSeq()).get();
	
		origReview.setContent(review.getContent());
		
		reviewRepo.save(origReview);
	}

	@Override
	public void deleteReview(Review review) {
		
		reviewRepo.deleteById(review.getSeq());

	}


	


}

