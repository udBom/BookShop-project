package com.ezen.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.book.domain.Event;
import com.ezen.book.domain.EventDTO;
import com.ezen.book.persistence.EventRepository;



@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepo;

	//원래 했던거 
	@Override
	public void getCheck(Event event) {
		return;

	}

	@Override
	public void saveCheck(Event event) {
		eventRepo.save(event);
		
	}

	@Override
	public List<EventDTO> getListEvents(String usreid) {
		List<EventDTO> dtoList = new ArrayList<EventDTO>();
		
		List<Event> eventList = eventRepo.getListEvents(usreid);
		for(Event event : eventList) {
			EventDTO eventDto = event.toDTO(event);
			dtoList.add(eventDto);
		}
		return dtoList;
	}
	
}
