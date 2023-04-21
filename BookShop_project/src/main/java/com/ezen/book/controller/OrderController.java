package com.ezen.book.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.book.domain.Book;
import com.ezen.book.domain.Cart;
import com.ezen.book.domain.Order;
import com.ezen.book.domain.OrderDetail;
import com.ezen.book.domain.User;
import com.ezen.book.service.BookService;
import com.ezen.book.service.CartService;
import com.ezen.book.service.OrderDetailService;
import com.ezen.book.service.OrderService;
import com.ezen.book.service.UserService;


@Controller
@RequestMapping("/order/")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService odService;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	//고객
	
	//안됨..
	@GetMapping("/getOrderList" )
	public String getOrderList(Model model, User user) {
		List<Order>orderList = orderService.getListOrderById("temp");
		System.out.println(orderList);
		for(Order od : orderList) {
			System.out.println(od.getOdList());
		}
		model.addAttribute("orderList", orderList);
		
		return "order/orderList";
	}
	
	@GetMapping("/getOrder")
	public String getOrder(Model model, Cart cseq) {
		Cart cart = cartService.findCartById(cseq.getCseq());
		List<Cart> cartList = new ArrayList<>();
		cartList.add(cart);
		model.addAttribute("amount", cart.getQuantity());
		model.addAttribute("user", cart.getUser());
		model.addAttribute("cartList", cartList);
		
		return "order/getOrder";
	}
	
	@PostMapping("/getOrder")
	public String getROrder(Model model, Order order, User user) {
		user = userService.findUserByid(user.getId());
		order.setUser(user);
		order.setOseq(orderService.selectMaxOseq());
		orderService.insertOrder(order);
		List<Cart> checkedList = cartService.getCheckedCartList(user.getId());
		int point = 0;
		for(Cart cart : checkedList) {
			//책 수량 변경
			Book book = cart.getBook();
			book.setQuantity(book.getQuantity() - cart.getQuantity());
			bookService.updateBook(book);
			//주문 상세 정보 저장
			OrderDetail od = new OrderDetail();
			od.setBook(cart.getBook());
			point += cart.getBook().getPer_point() * cart.getQuantity();
			od.setQuantity(cart.getQuantity());
			od.setOrder(order);
			odService.insertOrderDetail(od);
			cartService.deleteCart(cart.getCseq());
		}
		user.setPoint(user.getPoint() - order.getUsed_point() + point);
		userService.updatePoint(user);
		model.addAttribute("user", order.getUser());
		
		return "redirect:../cart/getCartList?user_id="+user.getId();
	}
	
	@GetMapping("/orderSelected")
	public String orderSelected(Model model, User user){
		List<Cart> checkedList = cartService.getCheckedCartList(user.getId());
		int amount = 0;
		for(Cart cart : checkedList) {
			user = cart.getUser();
			amount += cart.getQuantity();
		}
		model.addAttribute("amount", amount);
		model.addAttribute("user", user);
		model.addAttribute("cartList", checkedList);
		
		return "order/getOrder";
	}
	
	@PostMapping("/refundOrder")
	public String refundOrder(Order vo) {
		Order order = orderService.getOrderById(vo.getOseq());
		User user = order.getUser();
		int point = 0;
		for(OrderDetail od : order.getOdList()) {
			point += od.getBook().getPer_point() * od.getQuantity(); 
		}
		user.setPoint(user.getPoint()+order.getUsed_point() - point);
		orderService.updateOrderResult(order.getOseq(), 6);
		return "redirect:getOrderList";
	}
	
	@RequestMapping("/getDetail")
	public String getDetail(Model model, Order vo) {
		Order order = orderService.getOrderById(vo.getOseq());
		List<OrderDetail> odList = order.getOdList();
		int amount = 0;
		for(OrderDetail od : odList) {
			amount += od.getQuantity();
		}
		model.addAttribute("odList", odList);
		model.addAttribute("amount", amount);
		
		return "order/getOrderDetail";
	}
	
	
	//관리자
	
	@RequestMapping("/getOrderList_admin")
	public String getOrderList(Model model,
			@RequestParam(required = false, defaultValue = "0", value = "page") int page) {
		Page<Order> orderList = orderService.getOrderList(page);

		int totalPage = orderList.getTotalPages();

		model.addAttribute("orderList", orderList.getContent());
		model.addAttribute("totalPage", totalPage);
		return "order/getOrderList_admin";

	}

	@PostMapping("/updateOrder")
	public String updateOrder(Order order) {
		orderService.updateOrder(order);
		return "redirect:getOrderList";
	}

	@GetMapping("/getOrder_admin")
	public String getOrder(Order order, Model model) {
		model.addAttribute("orders", orderService.getOrder(order));
		return "order/getOrder_admin";
	}

	@GetMapping("/deleteOrder")
	public String deleteQna(Order order) {

		return "redirect:getOrder_admin";
	}
	


}
