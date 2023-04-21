package com.ezen.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.book.domain.Qna;
import com.ezen.book.domain.Reply;
import com.ezen.book.domain.User;
import com.ezen.book.service.ReplyService;




@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/getReply") //답글 페이지
	public String getUserQna(Reply reply, Model model) {
		model.addAttribute("reply",replyService.getQna(reply));
		return "/qna/getUserQna";
	}
	
	
	@GetMapping("/insertUserReply") // 답글 등록 페이지 불러오기
	public String insertUserReply() {
		
		return "/qna/getUserQna";
	} 
	
	@PostMapping("/insertUserReply")  // 답글 등록 처리
	public String insertUserReply(@RequestParam(value="seq") long seq,
							  @RequestParam(value="reply") String reply,
							  HttpSession session, RedirectAttributes model ) { //session에 member의 정보가 등록되어있어야 하기 때문이다.
		// login구현이 되어있을때 사용
		//Member loginUser = (Member)session.getAttribute("loginUser" );
		User loginUser = new User("doolly123",null,null,0,null,null); //강제로 member에 대한 값을 넣어주었을때! 
		
		Reply vo = new Reply();
		Qna qna = new Qna();
		
		qna.setSeq(seq);
		vo.setQna(qna);
		vo.setRecontent(reply);
		if(loginUser != null ) {
			vo.setUser(loginUser);
			replyService.insertReply(vo);
		}
		
		System.out.println("recontent = " + vo );
		
		model.addAttribute("seq",seq);
		
		return "redirect:/qna/getUserQna";  
	}
	
	

	@RequestMapping("/deleteUserReply")//답글 삭제,Qna 게시글의 번호도 받와야하기 때문에 
	public String deleteUserReply(Reply recontent, @RequestParam(value="seq") long seq, RedirectAttributes redirect) {
		
		replyService.deleteUserReply(recontent);
		
		Qna qna = new Qna();
		qna.setSeq(seq);
		redirect.addAttribute("qna", recontent.getQna());
		
		return "forward:/qna/getUserQna"; 
	}
	
	
}
