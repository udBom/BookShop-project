package com.ezen.book.controller;

import java.lang.reflect.Member;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.book.domain.Qna;
import com.ezen.book.domain.Reply;
import com.ezen.book.domain.User;
import com.ezen.book.service.QnaService;
import com.ezen.book.service.ReplyService;


@Controller
@RequestMapping("/qna/")
public class QnaController {

	@Autowired
	private QnaService qnaService;

	@Autowired
	private ReplyService replyService;

	//32~114 라인까지 Qna관리자 페이지  
	@RequestMapping("/getQnaList")
	public String getQnaList(Model model,
			@RequestParam(required = false, defaultValue = "0", value = "page") int page) {
		Page<Qna> qnaList = qnaService.getQnaList(page);

		int totalPage = qnaList.getTotalPages();

		model.addAttribute("qnaList", qnaList.getContent());
		model.addAttribute("totalPage", totalPage);
		return "qna/getQnaList";
	}

	@GetMapping("getQna")
	public String getQna(Qna qna, Reply reply,Model model) {
		model.addAttribute("qna", qnaService.getQna(qna));
		model.addAttribute("board", qna.getUsers());

		List<Reply> replyList = replyService.getReplyList();
		model.addAttribute("replyList", replyList);
		return "qna/getQna";
	}

	@PostMapping("updateQna")
	public String updateQna(Qna qna) {
		qnaService.updateQna(qna);
		return "redirect:getQnaList";
	}

	@GetMapping("deleteQna")
	public String deleteQna(Qna qna) throws ConstraintViolationException {
		try {
			qnaService.deleteQna(qna);
			return "redirect:getQnaList";
		} catch (Exception e) {
			return "qna/alert";
		}
	}

	@GetMapping("insertQna")
	public String insertQna() {
		return "qna/insertQna";
	}

	@PostMapping("insertQna")
	public String insertQna(Qna qna) {
		qnaService.insertQna(qna);
		return "redirect:getQnaList";
	}

	@GetMapping("insertReply")
	public String insertReply() {
		return "qna/getQna";
	}

	@PostMapping("insertReply")
	public String insertReply(@RequestParam(value = "seq") long seq, @RequestParam(value = "user_id") String user_id,
			@RequestParam(value = "reply") String content) {
		Qna qna = new Qna();
		qna.setSeq(seq);
		User user = new User();
		user.setId(user_id);
		Reply reply = new Reply();
		reply.setRecontent(content);

		reply.setQna(qna);
		reply.setUser(user);
		replyService.insertReply(reply);

		return "qna/alertOk";
	}

	@GetMapping("/deleteReply")
	public String deleteReply(Reply reply) {
		replyService.deleteReply(reply);
		return "qna/alertOk";
	}

	@PostMapping("updateReply")
	public String updateReply(Reply reply) {
		replyService.updateReply(reply);
		return "redirect:getQnaList";
	}
	
	//119~192 까지 고객 Qna 페이지 
	@GetMapping("/getUserQnaList") //페이징 리뷰 목록 화면 표시
	public String getUserQnaList(Model model,@RequestParam(required = false, defaultValue = "0", value = "page")int page) {
		Page<Qna> qnaList = qnaService.getUserQnaList(page);
		
		int totalPage = qnaList.getTotalPages();
		
		List<Qna> list = qnaList.getContent();
		
		System.out.println("<<< Qna List >>>");
		for (Qna rv : list) {
			System.out.println(rv);
		}
		
		model.addAttribute("qna",qnaList.getContent());
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("qnaList", list); //getUserQnaList의 86번째 줄과 이름이 동일해야한다.
		return "/qna/getUserQnaList";
	}
	
	
	
	
	@RequestMapping("/getUserQna") //리뷰 수정 및 삭제 페이지 & reply 등록 페이지
	public String getUserQna(Qna qna, Model model) {
		
		model.addAttribute("qna",qnaService.getQna(qna));
		
		model.addAttribute("replyList", replyService.getReplyListByQseq(qna.getSeq()));
		
		System.out.println(qnaService.getQna(qna));
		
		return "/qna/getUserQna";
	}
	
	
	@GetMapping("/insertUserQna") // 리뷰 등록 페이지 불러오기
	public String insertUserQna() {
		
		return "/qna/getUserQnaList";
	} 
	
	
	@PostMapping("/insertUserQna")  //Qna 등록 처리
	public String insertUserQna(Qna qna, HttpSession session) { //session에 member의 정보가 등록되어있어야 하기 때문이다.
		// login구현이 되어있을때 사용
		//Member loginUser = (Member)session.getAttribute("loginUser" );
		User loginUser = new User("doolly123",null,null,0,null,null); //강제로 member에 대한 값을 넣어주었을때! 
		
		if(loginUser != null ) {
			qna.setUser(loginUser);
			qnaService.insertUserQna(qna);
		}
		
		return "redirect:/qna/getUserQnaList"; 
	} 
	
	
	
	@PostMapping("/updateUserQna")
	public String updateUserQna(Qna qna) {
		qnaService.updateQna(qna);
		
		return "forward:/qna/getUserQna";
	}
	
	
	
	@GetMapping("/deleteUserQna")//QnA 삭제
	public String deleteUserQna(Qna qna) {
		qnaService.deleteQna(qna);
		
		return "forward:/qna/getUserQnaList";
	}

}
