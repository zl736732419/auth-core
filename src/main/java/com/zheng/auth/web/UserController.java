package com.zheng.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zheng.auth.annotation.AuthOper;
import com.zheng.auth.annotation.ModelMenu;
import com.zheng.auth.annotation.NavMenu;
import com.zheng.auth.annotation.Res;
import com.zheng.auth.domain.MenuPos;
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
@NavMenu(name="用户管理", href="/list", orderNum=1, psn="auth_root", icon="icon-door-group") //左边菜单
@Res(name="用户管理", orderNum=1, psn="auth_root", sn="user")
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@ModelMenu
	@AuthOper
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String showListPage() {
		return "user/list";
	}
	
	@AuthOper
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public Pager<User> list(UserQuery query) {
		Pager<User> pager = userService.findPagerByQuery(query);
		return pager;
	}
	
	@ModelMenu
	@AuthOper
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showAdd() {
		return "user/add";
	}
	
	@AuthOper
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(User user) {
		userService.save(user);
		return "redirect:/user/list";
	}
	
	@ModelMenu
	@AuthOper
	@RequestMapping(value="/{id}/update", method=RequestMethod.GET)
	public String showUpdate(@PathVariable Integer id) {
		User user = userService.findById(id);
		putRequestContext("user", user);
		return "user/update";
	}
	
	@ModelMenu(menuPos=MenuPos.MODEL_OPER)
	@AuthOper
	@RequestMapping(value="/{id}/update", method=RequestMethod.POST)
	public String update(@PathVariable Integer id, User user) {
		User dbUser = userService.findById(id);
		dbUser.setNickname(user.getNickname());
		dbUser.setStatus(user.getStatus());
		userService.update(user);
		return "redirect:/user/list";
	}
	
	@ModelMenu(menuPos=MenuPos.MODEL_OPER)
	@AuthOper
	@RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		userService.delete(id);
		return "redirect:/user/list";
	}
}
