package com.ezen.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.book.domain.Cart;
import com.ezen.book.service.BookService;
import com.ezen.book.service.CartService;
import com.ezen.book.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getCartList")
	public String getCartList(Model model, @RequestParam("user_id") String user_id) {
		List<Cart> cartList = cartService.getCartList(user_id);
		List<Cart> checkedCartList = cartService.getCheckedCartList(user_id);
		if(checkedCartList.size() == cartList.size()) {
			model.addAttribute("allChecked", 1);
		} else {
			model.addAttribute("allChecked", 0);
		}
		model.addAttribute("user_id", user_id);
		model.addAttribute("checkedCartList", checkedCartList);
		model.addAttribute("cartList", cartList);
		System.out.println(cartList);
		
		return "cart/Cart";
	}
	
	@RequestMapping("/insertCart")
	public String insertCart(	@RequestParam("book_id") int book_id, 
							@RequestParam("user_id") String user_id, 
							@RequestParam(name = "quantity",defaultValue = "1") int quantity,
							HttpServletRequest request) {
		String type = request.getParameter("cart");
		
		Cart cart = Cart.createCart(userService.findUserByid(user_id), bookService.findBookById(book_id));
		List<Cart> cartList = cartService.getCartList(user_id);
		int already = 0;
		for(Cart check : cartList) {
			if(check.getUser().getId().equals(user_id) && check.getBook().getId() == book_id){
				cart.setCseq(check.getCseq());
				cart.setQuantity(check.getQuantity()+quantity);
				already = 1;
				cartService.updateCart(cart);
			}
		}
		if(already == 0) {
			System.out.println("새로운 상품");
			cartService.insertCart(cart);
		}
		
		if(type != null) {
			return "redirect:getCartList?user_id="+user_id;
		}
		
		return "redirect:../getBook?id="+book_id;
	}
	
	@PostMapping(value = "/updateCart")
	public String updateCart(Model model, Cart cart) {
		cartService.updateCart(cart);
		List<Cart> cartList = cartService.getCartList(cart.getUser().getId());
		List<Cart> checkedCartList = cartService.getCheckedCartList(cart.getUser().getId());
		if(checkedCartList.size() == cartList.size()) {
			model.addAttribute("allChecked", 1);
		} else {
			model.addAttribute("allChecked", 0);
		}
		model.addAttribute("user_id", cart.getUser().getId());
		model.addAttribute("cartList", cartList);
		model.addAttribute("checkedCartList", checkedCartList);
		
		return "cart/Cart ::#listTable";		
	}
	
	@PostMapping(value = "/deleteCart")
	public String deleteCart(Model model, Cart cart) {
		cartService.deleteCart(cart.getCseq());
		List<Cart> cartList = cartService.getCartList(cart.getUser().getId());
		List<Cart> checkedCartList = cartService.getCheckedCartList(cart.getUser().getId());
		if(checkedCartList.size() == cartList.size()) {
			model.addAttribute("allChecked", 1);
		} else {
			model.addAttribute("allChecked", 0);
		}
		model.addAttribute("user_id", cart.getUser().getId());
		model.addAttribute("cartList", cartList);
		model.addAttribute("checkedCartList", checkedCartList);
		
		return "cart/Cart ::#listTable";		
	}
	
	@PostMapping(value = "checkedCart")
	public String checkCarted(Model model, Cart cart) {
		cartService.checkDataCart(cart);
		List<Cart> cartList = cartService.getCartList(cart.getUser().getId());
		model.addAttribute("cartList", cartList);
		List<Cart> checkedCartList = cartService.getCheckedCartList(cart.getUser().getId());
		if(checkedCartList.size() == cartList.size()) {
			model.addAttribute("allChecked", 1);
		} else {
			model.addAttribute("allChecked", 0);
		}
		model.addAttribute("user_id", cart.getUser().getId());
		model.addAttribute("checkedCartList", checkedCartList);
		
		return "cart/Cart ::#listTable";		
	}
	
	
	@PostMapping(value = "allCheckedCart")
	public String allCheckedCart(Model model, Cart cart) {
		List<Cart> cartList = cartService.getCartList(cart.getUser().getId());
		for(Cart tcart : cartList) {
			tcart.setChecked(cart.getChecked());
			cartService.checkDataCart(tcart);
		}
		List<Cart> checkedCartList = cartService.getCheckedCartList(cart.getUser().getId());
		if(checkedCartList.size() == cartList.size()) {
			model.addAttribute("allChecked", 1);
		} else {
			model.addAttribute("allChecked", 0);
		}
		model.addAttribute("user_id", cart.getUser().getId());
		model.addAttribute("cartList", cartList);
		model.addAttribute("checkedCartList", checkedCartList);
		
		return "cart/Cart ::#listTable";		
	}
	
	
}
