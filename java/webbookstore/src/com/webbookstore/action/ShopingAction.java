package com.webbookstore.action;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.webbookstore.bean.Book;
import com.webbookstore.bean.OrderItem;
import com.webbookstore.bean.Orders;
import com.webbookstore.bean.User;
import com.webbookstore.service.BookService;
import com.webbookstore.service.Cart;
import com.webbookstore.service.OrderService;

@SuppressWarnings("serial")
public class ShopingAction extends ActionSupport {
	
	private Integer bookId;
	private String quantity;
	
	private BookService bookService;
	private OrderService orderService;
	
	/**
	 * 放入购物车
	 */
	@SuppressWarnings("unchecked")
	public String addToCart() throws Exception{
		
		// 创建购物项
		Book book = this.bookService.getBookById(bookId);
		OrderItem orderItem = new OrderItem();
		orderItem.setQuantity(quantity);
		orderItem.setBook(book);
		// 取出购物车
		Map session = ActionContext.getContext().getSession();
		Cart cart = (Cart)session.get("cart");
		// 将购物项加入购物车
		cart.addBook(bookId, orderItem);
		
		return SUCCESS;
	}
	
	/**
	 * 更新购物车
	 */
	@SuppressWarnings("unchecked")
	public String updateToCart()throws Exception{
		
		// 取出购物车
		Map session = ActionContext.getContext().getSession();
		Cart cart = (Cart)session.get("cart");
		
		cart.updateCart(bookId, quantity);
		
		return SUCCESS;
	}
	
	/**
	 * 显示购物车
	 */
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 结帐
	 */
	@SuppressWarnings("unchecked")
	public String checkout() throws Exception{
		
		// 取出购物车
		Map session= (Map)ActionContext.getContext().getSession();
		Cart cart = (Cart)session.get("cart");
		// 取出用户信息
		User user = (User)session.get("user");
		if(cart != null && user != null){
			// 从购物车中取出订单项
			Collection<OrderItem> ordersItem = cart.getItems().values();
			// 创建一个订单
			Orders order = new Orders();
			order.setOrderDate(new Date());
			order.setUser(user);
			// 将购物车中的订单项放入订单中
			Set<OrderItem> _ordersItem = new HashSet<OrderItem>();
			for(OrderItem item : ordersItem){
				
				item.setOrders(order);
				_ordersItem.add(item);
			}
			order.setOrderItems(_ordersItem);
			// 将订单保存到数据库
			this.orderService.saveOrders(order);
			// 将订单放到request中
			Map request = (Map)ActionContext.getContext().get("request");
			request.put("order", order);
		}
		return SUCCESS;
	}
	
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
