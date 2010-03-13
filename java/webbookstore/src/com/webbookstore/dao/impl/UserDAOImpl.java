package com.webbookstore.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.webbookstore.bean.User;
import com.webbookstore.dao.UserDAO;
import com.webbookstore.util.BaseDAO;

public class UserDAOImpl implements UserDAO {
	
	/**
	 * 采用依赖注入的方式是用数据持久层基类
	 */
	private BaseDAO baseDAO;
	
	/**
	 * 增加用户
	 */
	public void saveUser(User user) {
		
		Session session = baseDAO.getSession();
		try{
			Transaction tx=session.beginTransaction();
			session.save(user);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/**
	 * 验证用户信息
	 */
	@SuppressWarnings("unchecked")
	public User validateUser(String username, String password) {
		
		Session session = baseDAO.getSession();
		
		StringBuffer hql = new StringBuffer("from com.webbookstore.bean.User as user ");
		hql.append("where user.username = :username ");
		hql.append("and user.password = :password ");
		
		Query query = session.createQuery(hql.toString());
		query.setString("username", username);
		query.setString("password", password);
		
		List<User> list = (List<User>)query.list();
		
		if(list != null && list.size()>0){
			User user = list.get(0);
			return user;
		}
		
		return null;
	}

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

}
