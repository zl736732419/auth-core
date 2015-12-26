package com.zheng.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zheng.auth.service.IInitService;

public class InitServiceTest extends BaseServiceTest {

	@Autowired
	private IInitService service;
	
	@Test
	public void testInit() {
		service.initEntityByXml("auth.xml");
	}
}
