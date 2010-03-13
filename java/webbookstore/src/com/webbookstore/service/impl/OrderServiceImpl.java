package com.webbookstore.service.impl;

import com.webbookstore.bean.Orders;
import com.webbookstore.dao.OrdersDAO;
import com.webbookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private OrdersDAO ordersDAO;
	
	/**
	 * 保存订单
	 */
	public void saveOrders(Orders orders) {
		
		this.ordersDAO.saveOrders(orders);
	}

	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}

	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}

}
