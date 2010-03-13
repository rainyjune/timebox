package com.webbookstore.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.webbookstore.bean.Orders;
import com.webbookstore.dao.OrdersDAO;
import com.webbookstore.util.BaseDAO;

public class OrdersDAOImpl implements OrdersDAO {
	
	private BaseDAO baseDAO;
	
	/**
	 * 保存订单
	 */
	public void saveOrders(Orders orders) {
		
		Session session = baseDAO.getSession();
		
		Transaction tx = session.beginTransaction();
		try{
			session.save(orders);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.notifyAll();
			e.printStackTrace();
		}
	}
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}
	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

}
