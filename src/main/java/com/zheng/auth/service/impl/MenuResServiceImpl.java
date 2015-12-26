package com.zheng.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zheng.auth.dao.IBaseDao;
import com.zheng.auth.dao.IMenuResDao;
import com.zheng.auth.domain.MenuResource;
import com.zheng.auth.dto.LeftMenuDto;
import com.zheng.auth.service.IMenuResService;

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

}
