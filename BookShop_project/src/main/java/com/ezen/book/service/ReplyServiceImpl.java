package com.ezen.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezen.book.domain.Reply;
import com.ezen.book.persistence.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyRepository replyRepo;
	
	@Override
	public void insertReply(Reply reply) {
		replyRepo.save(reply);
		
	}

	@Override
	public void deleteReply(Reply reply) {
		replyRepo.deleteById(reply.getReseq());
	}

	@Override
	public void updateReply(Reply reply) {
		Reply findReply=replyRepo.findById(reply.getReseq()).get();
		
		findReply.setQna(reply.getQna());
		findReply.setRecontent(reply.getRecontent());
		findReply.setReseq(reply.getReseq());
		findReply.setUser(reply.getUsers());
		replyRepo.save(findReply);
	}

	@Override
	public List<Reply> getReplyList() {
		return replyRepo.findAll();
	}

	@Override
	public Page<Reply> getQna(int page) {
		Pageable pageable = PageRequest.of(page, 5,Sort.by(Sort.Direction.DESC,"seq")); //page라는 변수를 사용하여 다음 페이지로 넘어가도록 int page 생성
		
		return replyRepo.findAll(pageable);
	}

	@Override
	public Reply getQna(Reply reply) {
		return replyRepo.findById(reply.getReseq()).get();
	}

	@Override
	public List<Reply> getReplyListByQseq(long reseq) {
		return replyRepo.findByQnaSeq(reseq);
	}

	@Override
	public void insertUserReply(Reply reply) {
		replyRepo.save(reply);
		
	}

	@Override
	public void updateUserReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

}
