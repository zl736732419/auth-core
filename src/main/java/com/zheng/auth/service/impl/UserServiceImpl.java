package com.zheng.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zheng.auth.dao.IBaseDao;
import com.zheng.auth.dao.IUserDao;
import com.zheng.auth.domain.User;
import com.zheng.auth.service.IUserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements
		IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	protected IBaseDao<User> getBaseDao() {
		return userDao;
	}

	

}
