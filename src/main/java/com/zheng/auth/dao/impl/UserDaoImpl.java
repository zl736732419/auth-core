package com.zheng.auth.dao.impl;

import org.springframework.stereotype.Repository;

import com.zheng.auth.dao.IUserDao;
import com.zheng.auth.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

}
