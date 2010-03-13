package com.webbookstore.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.webbookstore.bean.User;
import com.webbookstore.service.Cart;
import com.webbookstore.service.UserService;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

	private User user;
	private UserService userService;

	/**
	 * 用户登录的处理
	 */
	@Override
	public String execute() throws Exception {

		// 获取登陆信息
		String username = this.user.getUsername();
		String password = this.user.getPassword();

		// 验证登陆
		User resultUser = this.userService.validateUser(username, password);

		// 判断登陆是否成功
		if (resultUser != null) {

			// 将用户信息放到Session中去
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.put("user", resultUser);

			// 将购物车放到Session中去
			Cart cart = new Cart();
			session.put("cart", cart);

			return SUCCESS;
		} else {

			return ERROR;
		}

	}

	/**
	 * 注册用户
	 */
	public String register() {

		// 保存用户信息
		this.userService.saveUser(this.user);

		return SUCCESS;
	}

	/**
	 * 退出
	 */
	@SuppressWarnings("unchecked")
	public String logout() throws Exception {

		Map session = ActionContext.getContext().getSession();
		session.remove("user");
		session.remove("cart");
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
