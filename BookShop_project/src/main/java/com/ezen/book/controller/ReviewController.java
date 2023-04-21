package com.ezen.book.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.book.domain.Review;
import com.ezen.book.domain.User;
import com.ezen.book.service.ReviewService;


@Controller
@RequestMapping("/review/")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value="/index")
	public String index() {
		return "index";
	} 
	
	
	@GetMapping("/getReviewList") //페이징 리뷰 목록 화면 표시
	public String getReviewList(Model model,@RequestParam(required = false, defaultValue = "0", value = "page")int page) {
		Page<Review> reviewList = reviewService.getReviewList(page);
		
		int totalPage = reviewList.getTotalPages();
		
		List<Review> list = reviewList.getContent();
		
		System.out.println("<<< Review List >>>");
		for (Review rv : list) {
			System.out.println(rv);
		}
		
		model.addAttribute("review",reviewList.getContent());
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("reviewList", list);
		return "review/getReviewList";
	}
	
	
	
	
	@RequestMapping("/getReview") //리뷰 수정 및 삭제 페이지
	public String getReview(Review review, Model model) {
		model.addAttribute("review",reviewService.getReview(review));
		return "review/getReview";
	}
	
	
	@GetMapping("/insertReview") // 리뷰 등록 페이지 불러오기
	public String insertReview() {
		
		return "getReviewList";
	} 
	
	@PostMapping("/insertReview")  // 리뷰 등록 처리
	public String insertReview(Review review, HttpSession session) { //session에 user의 정보가 등록되어있어야 하기 때문이다.
		// login구현이 되어있을때 사용
		//User loginUser = (User)session.getAttribute("loginUser" );
		User loginUser = new User("doolly123",null,null,0,null,null); //강제로 user에 대한 값을 넣어주었을때!  //"doolly123",null,null,0,null,null
		
		if(loginUser != null ) {
			review.setUsers(loginUser);
			reviewService.insertReview(review);
		}
		
		return "redirect:/getReviewList"; //"redirect:getReviewList"
	}
	
	
	@PostMapping("/updateReview") //리뷰 수정
	public String updateReview(Review review) {
		reviewService.updateReview(review);
		
		return "forward:getReview";
	}
	
	@GetMapping("/deleteReview")//리뷰 삭제
	public String deleteReview(Review review) {
		reviewService.deleteReview(review);
		
		return "forward:getReviewList";
	}
	
	
}
