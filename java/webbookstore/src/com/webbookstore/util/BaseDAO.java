package com.webbookstore.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDAO {
	
	private SessionFactory sessionFactory;
	
	/**
	 * 获取Session
	 */
	public Session getSession(){
		
		return sessionFactory.openSession();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
