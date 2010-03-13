package com.webbookstore.dao;

import com.webbookstore.bean.Orders;

public interface OrdersDAO {
	
	/**
	 * 保存订单
	 */
	public void saveOrders(Orders orders);
}
