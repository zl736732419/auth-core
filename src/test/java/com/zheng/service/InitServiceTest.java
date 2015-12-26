package com.zheng.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zheng.auth.domain.MenuResource;
import com.zheng.auth.dto.LeftMenuDto;
import com.zheng.auth.service.IControllerResService;
import com.zheng.auth.service.IInitService;
import com.zheng.auth.service.IMenuResService;

public class InitServiceTest extends BaseServiceTest {

	@Autowired
	private IInitService service;
	@Autowired
	private IControllerResService controllerResService;
	@Autowired
	private IMenuResService menuService;
	
	@Test
	public void testInit() {
		service.initEntityByXml("auth.xml");
	}
	
	@Test
	public void testControllerResource() {
		controllerResService.initControllerRes(new String[] {"com.zheng.auth.web"});
	}
	
	@Test
	public void testMenuResource() {
		menuService.initMenuResources(new String[] {"com.zheng.auth.web"});
	}
	
	@Test
	public void testLeftMenu() {
		List<LeftMenuDto> list = menuService.listLeftNav();
		for(LeftMenuDto m : list) {
			System.out.println(m.getParent().getName());
			for(MenuResource child : m.getChildren()) {
				System.out.println("----" + child.getName());
			}
		}
	}
}
