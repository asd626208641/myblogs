package com.jxau.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxau.entity.User;
import com.jxau.mapper.UserMapper;
import com.jxau.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public boolean checkUser(User user) {
		// TODO Auto-generated method stub
		List<User> users = userMapper.selectList(null);
		for (User u : users) {
			if (user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //根据username和password查询avatar和nickname字段
        queryWrapper
	        	.select("id","email","avatar","nickname")
	        	.ge("username", user.getUsername())
	        	.ge("password", user.getPassword());
		User u = userMapper.selectOne(queryWrapper);
		return u;
	}

}
