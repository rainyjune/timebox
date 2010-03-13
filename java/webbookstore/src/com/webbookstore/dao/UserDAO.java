package com.webbookstore.dao;

import com.webbookstore.bean.User;

public interface UserDAO {
	/**
	 * 增加用户
	 */
	public void saveUser(User user);
	/**
	 * 验证用户信息
	 */
	public User validateUser(String username , String password);
}
