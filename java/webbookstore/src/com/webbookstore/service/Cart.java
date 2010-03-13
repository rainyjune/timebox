package com.webbookstore.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.webbookstore.bean.Book;
import com.webbookstore.bean.OrderItem;

/**
 * 购物车
 */
public class Cart {
	
	// 购物项，书ID-->订单项
	public Map<Integer,OrderItem> items;
	
	/**
	 * 购物车初始化，默认的构造函数
	 */
	public Cart(){
		if(items == null){
			items = new HashMap<Integer,OrderItem>();
		}
	}
	
	/**
	 * 添加购物项
	 */
	public void addBook(Integer bookId,OrderItem orderItem){
		
		// 判断是否已经存在购物信息
		if(items.containsKey(bookId)){
			OrderItem _orderItem = items.get(bookId);
			Integer orginQuantity = Integer.parseInt(_orderItem.getQuantity());
			Integer addQuantity = Integer.parseInt(orderItem.getQuantity());
			Integer result = orginQuantity+addQuantity;
			_orderItem.setQuantity(result.toString());
		}else{
			items.put(bookId, orderItem);
		}
	}
	
	/**
	 * 修改购物项
	 */
	public void updateCart(Integer bookId,String quantity){
		OrderItem orderItem = items.get(bookId);
		orderItem.setQuantity(quantity);
		items.put(bookId, orderItem);
	}
	
	/**
	 * 计算总金额
	 */
	public int getTotalPrice(){
		
		int totalPrice = 0;
		
		// 取出所有订单项
		Iterator<OrderItem> its = items.values().iterator();
		
		// 遍历订单项计算总金额
		while(its.hasNext()){
			OrderItem it=its.next();
			// 获取书的价格
			Book book = it.getBook();
			int price = book.getPrice();
			// 获取书的数量
			int quantity = Integer.parseInt(it.getQuantity());
			// 计算金额
			totalPrice += price*quantity; 
		}
		
		return totalPrice;
	}
	
	/**
	 * 获取所有订单项
	 */
	public Map<Integer, OrderItem> getItems() {
		return items;
	}
}
