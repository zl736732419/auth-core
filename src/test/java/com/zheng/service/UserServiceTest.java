package com.zheng.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zheng.auth.domain.User;
import com.zheng.auth.dto.Pager;
import com.zheng.auth.dto.UserQuery;
import com.zheng.auth.service.IUserService;

public class UserServiceTest extends BaseServiceTest {

	@Autowired
	private IUserService userService;
	
	@Test
	public void testList() {
		UserQuery query = new UserQuery();
		query.setUsername("root");
		query.setNickname("xiao");
		
		Pager<User> pager = userService.findPagerByQuery(query);
		
		System.out.println(pager);
	}
	
	
}
