package com.ezen.book.domain; 

import java.lang.reflect.Member;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long eseq;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date regdate = new Date();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_ID",nullable=false, updatable=false) //nullable=false는 joinColumn에 필요!
	private User users;
	
	public EventDTO toDTO(Event event) {
		EventDTO eventDto = EventDTO.builder()
				.title("♡출석♡")
				.start(event.getRegdate())
				.end(event.getRegdate())
				.allDay(false)
				.build();
		
		return eventDto;
	}
}
