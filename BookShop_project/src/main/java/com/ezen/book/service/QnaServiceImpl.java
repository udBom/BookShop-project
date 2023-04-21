package com.ezen.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezen.book.domain.Qna;
import com.ezen.book.persistence.QnaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaRepository qnaRepo;
	
	@Override
	public void insertQna(Qna qna) {
		qnaRepo.save(qna);
	}

	@Override
	public void updateQna(Qna qna) {
		Qna findqna = qnaRepo.findById(qna.getSeq()).get();
		
		findqna.setContent(qna.getContent());
		findqna.setTitle(qna.getTitle());
		findqna.setGenre(qna.getGenre());
		qnaRepo.save(findqna);
	}

	@Override
	public void deleteQna(Qna qna) {
		qnaRepo.deleteById(qna.getSeq());
	}		

	@Override
	public Qna getQna(Qna qna) {
		return qnaRepo.findById(qna.getSeq()).get();
	}

	@Override
	public Page<Qna> getQnaList(int page) {
		
		return qnaRepo.findAll(PageRequest.of(page,10,Sort.Direction.DESC,"seq"));
	}

	//Qna 사용자 ServiceImpl 
	@Override
	public Page<Qna> getUserQnaList(int page) {
		Pageable pageable = PageRequest.of(page, 5,Sort.by(Sort.Direction.DESC,"seq")); //page라는 변수를 사용하여 다음 페이지로 넘어가도록 int page 생성
		
		return qnaRepo.findAll(pageable);
	}

	@Override
	public Qna getUserQna(Qna qna) {
		return qnaRepo.findById(qna.getSeq()).get();
	}

	@Override
	public void insertUserQna(Qna qna) {
		qnaRepo.save(qna);
		
	}

	@Override
	public void updateUserQna(Qna qna) {
		Qna origQna = qnaRepo.findById(qna.getSeq()).get();
		
		//origQna.setTitle(qna.getTitle());
		origQna.setContent(qna.getContent());
		
		qnaRepo.save(origQna);
		
	}

	@Override
	public void deleteUserQna(Qna qna) {
		qnaRepo.deleteById(qna.getSeq());
		
	}

	@Override
	public void insertUserQnaReply(Qna qna) {
		qnaRepo.save(qna);
	}
	
	
	
	
	

}
