package com.zheng.auth.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zheng.auth.annotation.ModelMenu;
import com.zheng.auth.annotation.NavMenu;
import com.zheng.auth.dao.IBaseDao;
import com.zheng.auth.dao.IMenuResDao;
import com.zheng.auth.domain.MenuPos;
import com.zheng.auth.domain.MenuResource;
import com.zheng.auth.dto.LeftMenuDto;
import com.zheng.auth.service.IMenuResService;
import com.zheng.auth.util.BasicSysKit;

@Service
public class MenuResServiceImpl extends BaseServiceImpl<MenuResource> implements
		IMenuResService {

	@Autowired
	private IMenuResDao dao = null;

	@Override
	protected IBaseDao<MenuResource> getBaseDao() {
		return dao;
	}

	@Override
	public void add(MenuResource mr, String psn) {
		dao.add(mr, psn);
	}

	@Override
	public List<MenuResource> listModelMenuByType(String psn, int pos) {
		return dao.listModelMenuByType(psn, pos);
	}

	@Override
	public List<MenuResource> listTopMenu() {
		return dao.listTopMenu();
	}

	@Override
	public List<LeftMenuDto> listLeftNav() {
		return dao.listLeftNav();
	}

	@Override
	public List<MenuResource> listByParent(Integer pid) {
		return dao.listByParent(pid);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initMenuResources(String[] packages) {
		List<Class> clazs = new ArrayList<>();
		List<Class> tmpClzs = null;
		for(String p:packages) {
			tmpClzs = BasicSysKit.listByClass(p);
			BasicSysKit.mergeList(clazs, tmpClzs);
		}
		for(Class c:clazs) {
			//添加顶部或者左部的导航菜单
			addTopNavMenu(c);//方法上标注有NavMenu
			if(c.isAnnotationPresent(NavMenu.class)) { //添加左边菜单 类上标注有NavMenu
				//如果在类上面有NavMenu存在，就添加导航菜单和子菜单
				MenuResource mr = addLeftNavMenu(c);
				addModelMenu(mr,c);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private MenuResource addLeftNavMenu(Class c) {
		String path = null;
		if(c.isAnnotationPresent(RequestMapping.class)) {
			path = ((RequestMapping)c.getAnnotation(RequestMapping.class)).value()[0];
		}
		String cname = c.getSimpleName();
		NavMenu nm = (NavMenu)c.getAnnotation(NavMenu.class);
		String sn = getMenuSn(cname, null, nm.sn());
		MenuResource mr = dao.loadBySn(sn, MenuResource.class);
		if(mr==null) mr = new MenuResource();
		mr.setDisplay(nm.display());
		mr.setHref(path+nm.href());
		mr.setIcon(nm.icon());
		mr.setMenuPos(nm.menuPos());
		mr.setName(nm.name());
		mr.setOrderNum(nm.orderNum());
		mr.setSn(sn);
		add(mr, nm.psn());
		return mr;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addModelMenu(MenuResource pmr, Class c) {
		String path = null;
		String cname = c.getSimpleName();
		Method[] ms = c.getDeclaredMethods();
		MenuResource mr = null;
		for(Method m:ms) {
			if(m.isAnnotationPresent(ModelMenu.class)) {
				String mname = m.getName();
				if(c.isAnnotationPresent(RequestMapping.class)) {
					path = ((RequestMapping)c.getAnnotation(RequestMapping.class)).value()[0];
				}
				ModelMenu mm = m.getAnnotation(ModelMenu.class);
				RequestMapping rm = m.getAnnotation(RequestMapping.class);
				path+=rm.value()[0];
				String sn = getMenuSn(cname, mname, mm.sn());
				mr = dao.loadBySn(sn, MenuResource.class);
				if(mr==null) mr = new MenuResource();
				mr.setDisplay(mm.display());
				mr.setHref(path);
				mr.setIcon(mm.icon());
				mr.setMenuPos(mm.menuPos());
				mr.setName(getModelMenuName(mname,mm));
				mr.setOrderNum(mm.orderNum());
				mr.setSn(sn);
				
				this.add(mr, pmr.getSn());
			}
		}
	}
	
	private String getModelMenuName(String mname, ModelMenu mm) {
		String name = mm.name();
		if(!BasicSysKit.isEmpty(name)) return name;
		if(mname.startsWith("add")) return "添加";
		else if(mname.startsWith("update")) return "更新";
		else if(mname.startsWith("delete")) return "删除";
		else  if(mname.startsWith("show")) return "查询";
		else if(mname.startsWith("list")) return "列表";
		return "";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addTopNavMenu(Class c) {
		String path = null;
		Method[] ms = c.getDeclaredMethods();
		String cname = c.getSimpleName();
		String sn = null;
		MenuResource mr = null;
		for(Method m:ms) {
			String mname = m.getName();
			if(m.isAnnotationPresent(NavMenu.class)) {
				if(c.isAnnotationPresent(RequestMapping.class)) {
					path = ((RequestMapping)c.getAnnotation(RequestMapping.class)).value()[0];
				}
				path+=((RequestMapping)m.getAnnotation(RequestMapping.class)).value()[0];
				NavMenu nm = m.getAnnotation(NavMenu.class);
				sn = getMenuSn(cname,mname,nm.sn());
				mr = dao.loadBySn(sn, MenuResource.class);
				if(mr==null) {
					mr = new MenuResource();
				}
				mr.setDisplay(nm.display());
				mr.setHref(path);
				mr.setIcon(nm.icon());
				mr.setMenuPos(MenuPos.NAV_TOP);
				mr.setName(nm.name());
				mr.setOrderNum(nm.orderNum());
				mr.setSn(sn);
				this.add(mr, nm.psn());
			}
			
		}
	}
	
	private String getMenuSn(String cname,String mname,String asn) {
		String sn = null;
		if(BasicSysKit.isEmpty(asn)) {
			if(BasicSysKit.isEmpty(mname)) sn = cname;
			else sn = cname+"."+mname;
		}
		else sn = asn;
		return sn;
	}
}
