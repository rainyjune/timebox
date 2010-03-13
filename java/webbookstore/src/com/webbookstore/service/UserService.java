package com.webbookstore.service;

import com.webbookstore.bean.User;

public interface UserService {
	/**
	 * 注册用户
	 */
	public void saveUser(User user);
	/**
	 * 验证用户
	 */
	public User validateUser(String username,String password);
}
