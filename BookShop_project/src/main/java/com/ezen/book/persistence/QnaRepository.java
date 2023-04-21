package com.ezen.book.persistence;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.book.domain.Qna;


public interface QnaRepository extends JpaRepository<Qna, Long>{
	
	@Query(value="SELECT q FROM qna q", nativeQuery = true)
	Page<Qna> getQnaList(Pageable page);
	
	Page<Qna> findAll(Pageable pageable);

}
