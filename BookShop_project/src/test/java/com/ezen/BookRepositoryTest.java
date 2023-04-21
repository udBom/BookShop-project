package com.ezen;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.book.domain.Book;
import com.ezen.book.domain.Order;
import com.ezen.book.domain.Search;
import com.ezen.book.persistence.OrderRepository;
import com.ezen.book.persistence.UtilRepository;
import com.ezen.book.service.BookService;

@SpringBootTest
public class BookRepositoryTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private OrderRepository orderRepo;
	
	/*
	 * 
	@Autowired
	private BookRepositoryImpl bookRepo;
	
	@Disabled
	@Test 
	public void testInsert() {
		
		for(int i=1; i<=5; i++) {
			Book book = new Book();
			Calendar cal = Calendar.getInstance();
			cal.set(2017+i, (i*2)-2, i*5);
			
			book.setType("국내");
			book.setTitle("책 제목 "+i);
			book.setPrice(10000+(i*4000));
			book.setAuthor("저자 "+i);
			book.setPublisher("미래출판사");
			book.setContent("내용"+i);
			book.setImage("책 제목"+i+".jpg");
			book.setPublishing_date(cal.getTime());
			book.setPurchase(i * 4);
			book.setQuantity((i*4)-4);
			book.setPer_point(10);
			bookRepo.save(book);
		}
		for(int i=1; i<=5; i++) {
			Book book = new Book();
			Calendar cal = Calendar.getInstance();
			cal.set(2017+i, 1+(i*2), i*4);
			
			book.setType("해외");
			book.setTitle("책 제목 "+(i+5));
			book.setPrice(10000+(i*3000));
			book.setAuthor("저자 "+(i+5));
			book.setPublisher("문화출판사");
			book.setContent("내용"+(i+5));
			book.setImage("책 제목"+(i+5)+".jpg");
			book.setPublishing_date(cal.getTime());
			book.setPurchase(i * 3);
			book.setQuantity((i*3)-3);
			book.setPer_point(10);
			bookRepo.save(book);
		}
		 
		
		Book book = new Book();
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 11, 1);
		
		book.setType("국내");
		book.setTitle("이펙티브 자바 3/E");
		book.setPrice(36000);
		book.setAuthor("조슈아 블로크 저자, 이복연 번역");
		book.setPublisher("인사이트");
		book.setContent("자바 플랫폼 모범 사례 완벽 가이드 - Java 7, 8, 9 대응\r\n"
				+ "자바 6 출시 직후 출간된 『이펙티브 자바 2판』 이후로 자바는 커다란 변화를 겪었다. 그래서 졸트상에 빛나는 이 책도 자바 언어와 라이브러리의 최신 기능을 십분 활용하도록 내용 전반을 철저히 다시 썼다. 모던 자바가 여러 패러다임을 지원하기 시작하면서 자바 개발자들에게는 구체적인 모범 사례가 더욱 절실해졌고, 관련 조언을 이 책에 담아낸 것이다.\r\n"
				+ "3판에는 자바 7, 8, 9에서 자바 언어와 라이브러리에 추가된 특성들을 녹여냈다. 특히 그동안 객체 지향에 치중하던 자바에 새로 도입된 함수형 프로그래밍 요소도 자세히 알아본다. 람다(lambda)와 스트림(stream)만을 다룬 장을 포함하여 새로운 아이템도 많이 추가되었다.");;
		book.setImage("이펙티브 자바 3E.jpg");
		book.setPublishing_date(cal.getTime());
		book.setPurchase(50);
		book.setQuantity(0);
		book.setPer_point(10);
		bookRepo.save(book);
	}
	
	@Test
	public void getBookListTest() {
		
		String type = null;
		
		Integer bookType = null;
		
		if(type != null) {
			bookType = Integer.valueOf(type);
		}
		
		String bookKind = "new";
		List<Book> bookList = bookService.getBookList(bookKind,bookType);
		
		for(Book printList : bookList) {
			System.out.println(printList);
		}
	}
	
	@Test
	public void getSearchListTest() {
		Search search = new Search();
		
		search.setSearchCondition("all");
		search.setSearchKeyword("제목");
		
		List<Book> bookList = bookService.getSearchList(search);
		
		for(Book printList : bookList) {
			
			System.out.println(printList);
		}
	}
	 */
	 
	
	  
	
}
