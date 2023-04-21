package com.ezen.book.service;

import org.springframework.data.domain.Page;

import com.ezen.book.domain.Qna;

public interface QnaService {

	//OrderQna
	void insertQna(Qna qna);
	void updateQna(Qna qna);
	void deleteQna(Qna qna);
	Qna getQna(Qna qna);
	Page<Qna> getQnaList(int page);
	
	//UserQna
	Page<Qna> getUserQnaList(int page); //페이징 처리가 된 리뷰 목록! page를 변수로 칭해줘야 다음 페이지의 화면이 출력된다.
	
	Qna getUserQna(Qna qna); //qna 상세 보기 페이지
	
	void insertUserQna(Qna qna);

	void updateUserQna(Qna qna);
	
	void deleteUserQna(Qna qna);

	void insertUserQnaReply(Qna qna);

}
