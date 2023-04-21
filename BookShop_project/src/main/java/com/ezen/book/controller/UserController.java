package com.ezen.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.book.domain.User;
import com.ezen.book.service.UserService;


@Controller
@RequestMapping("/user/")
public class UserController {

		@Autowired
		private UserService userService;
		
		@GetMapping(value="getUserList")
		public String getUserList(Model model, @RequestParam(required = false, defaultValue = "0",value="page")int page) {
			Page<User> userList = userService.getUserList(page);
			
			int totalPage = userList.getTotalPages();
			
			model.addAttribute("userList",userList.getContent());
			model.addAttribute("totalPage",totalPage);
			return "user/getUserList";	
			
		}
		
		@GetMapping("getUser")
		public String getUser(User user, Model model) {
			model.addAttribute("user",userService.getUser(user));
			
			return "user/getUser";
			
		}
		
		@GetMapping("deleteUser")
		public String deleteUser(User user) {
			userService.deleteUser(user);
			
			return "redirect:getUserList";
		}
		
		@PostMapping("updateUser")
		public String updateUser(User user) {
			userService.updateUser(user);
			
			return "redirect:getUserList";		
		}
}
