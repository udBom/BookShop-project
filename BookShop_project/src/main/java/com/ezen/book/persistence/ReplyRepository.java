package com.ezen.book.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.book.domain.Reply;


public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Query(value="SELECT r FROM qna r", nativeQuery = true)
	Page<Reply> getReplyList(Pageable page);
	
	Page<Reply> findAll(Pageable pageable);
	
	@Query(value="SELECT * FROM Reply WHERE seq=?1", nativeQuery = true)
	List<Reply> findByQnaSeq(long seq);
}
