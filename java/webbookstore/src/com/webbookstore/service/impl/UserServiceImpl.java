package com.webbookstore.service.impl;

import com.webbookstore.bean.User;
import com.webbookstore.dao.UserDAO;
import com.webbookstore.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO;
	/**
	 * 注册用户
	 */
	public void saveUser(User user) {
				
		this.userDAO.saveUser(user);
	}
	
	/**
	 * 验证用户
	 */
	public User validateUser(String username, String password) {
		
		User user = this.userDAO.validateUser(username, password);
		
		//System.out.println("取出用户信息--------"+user.getUserid());
		return user;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
