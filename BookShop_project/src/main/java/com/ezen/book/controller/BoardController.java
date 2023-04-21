package com.ezen.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.book.domain.Book;
import com.ezen.book.service.BoardService;


@Controller
@RequestMapping("/board/")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("getBoardList")
	public String getBoardList(Model model, @RequestParam(required = false, defaultValue = "0",value="page")int page) {
		Page<Book> boardList = boardService.getBoardList(page);
		
		int totalPage = boardList.getTotalPages();
		
		model.addAttribute("boardList",boardList.getContent());
		model.addAttribute("totalPage",totalPage);
		return "board/getBoardList";
	}
	
	@GetMapping("getBoard")
	public String getBoard(Book book,Model model) {
		model.addAttribute("book",boardService.getBoard(book));
		
		return "board/getBoard";
	}
	
	@PostMapping("updateBoard")
	public String updateBoard(Book book) {
		boardService.updateBoard(book);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Book book) {
		boardService.deleteBoard(book);
		return "redirect:getBoardList";
	}

	
	@GetMapping("insertBoard")
	public String insertBoard() {
		return "board/insertBoard";
	}
	@PostMapping("insertBoard")
	public String insertBoard(Book book,MultipartFile file) throws Exception {
		String fileName=file.getOriginalFilename();
		book.setImage(fileName);
		boardService.insertBoard(book, file);
		return "redirect:getBoardList";
	}
}
