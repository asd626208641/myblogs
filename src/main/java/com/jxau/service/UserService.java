package com.jxau.service;

import com.jxau.entity.User;

public interface UserService {
	/*
	 * 检验用户
	 */
	public boolean checkUser(User user);
	
	/*
	 * 检验后获取用户详细信息(除账号密码)
	 */
	public User getUser(User user);
	
}
