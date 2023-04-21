package com.ezen.book.persistence;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ezen.book.domain.Event;




public interface EventRepository extends CrudRepository<Event, Long>{
	
	@Query(value="SELECT * FROM Event WHERE member_id=?1", nativeQuery = true)
	public List<Event> getListEvents(String member_id);
}
