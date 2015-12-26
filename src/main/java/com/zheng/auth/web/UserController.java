package com.zheng.auth.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zheng.auth.domain.User;
import com.zheng.auth.dto.Pager;
import com.zheng.auth.dto.UserQuery;
import com.zheng.auth.service.IUserService;

/**
 * 用户管理数据交互接口
 *
 * @author zhenglian
 * @time 2015年12月25日 下午2:06:32
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getListPage() {
		return "user/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public Pager list(UserQuery query) {
		Pager<User> pager = userService.findPagerByQuery(query);
		return pager;
	}
	
}
