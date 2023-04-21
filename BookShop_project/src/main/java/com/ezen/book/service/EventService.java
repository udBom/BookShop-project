package com.ezen.book.service;

import java.util.List;

import com.ezen.book.domain.Event;
import com.ezen.book.domain.EventDTO;




public interface EventService {
	
	void getCheck(Event event);

	void saveCheck(Event event);

	List<EventDTO> getListEvents(String usreid);
	 

	 
	
}
