package com.ezen.book.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ezen.book.domain.Reply;


public interface ReplyService {
	//관리자 reply
	void insertReply(Reply reply);
	void deleteReply(Reply reply);
	void updateReply(Reply reply);
	List<Reply> getReplyList();
	
	//사용자 reply
	Page<Reply> getQna(int page); //페이징 처리가 된 리뷰 목록! page를 변수로 칭해줘야 다음 페이지의 화면이 출력된다.
	
	Reply getQna(Reply reply); //리뷰 상세 보기 페이지
	
	List<Reply> getReplyListByQseq(long reseq);
	
	void insertUserReply(Reply reply);

	void updateUserReply(Reply reply);
	
	void deleteUserReply(Reply reply);
}
