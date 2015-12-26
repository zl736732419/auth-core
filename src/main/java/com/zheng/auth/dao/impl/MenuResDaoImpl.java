package com.zheng.auth.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.zheng.auth.dao.IMenuResDao;
import com.zheng.auth.domain.MenuPos;
import com.zheng.auth.domain.MenuResource;
import com.zheng.auth.dto.LeftMenuDto;

@Repository
public class MenuResDaoImpl extends BaseDaoImpl<MenuResource> implements
		IMenuResDao {

	private String getSelectHql() {
		String hql = "select new MenuResource(m.id, m.name, m.sn, m.psn, m.menuPos, m.href, m.icon, m.orderNum, m.display)";
		return hql;
	}

	@Override
	public void add(MenuResource mr, String psn) {
		if (!StringUtils.isBlank(psn)) {
			MenuResource parent = loadBySn(psn, MenuResource.class);
			if (parent == null) {
				throw new RuntimeException("菜单的父类不存在");
			}
			mr.setParent(parent);
			mr.setPsn(psn);
		}

		// 设置排序号
		MenuResource self = loadBySn(mr.getSn(), MenuResource.class);
		if (self == null) {
			self = mr;
		} else {
			// 存在就进行修改
			self.setDisplay(mr.getDisplay());
			self.setHref(mr.getHref());
			self.setIcon(mr.getIcon());
			self.setMenuPos(mr.getMenuPos());
			self.setName(mr.getName());
			self.setOrderNum(mr.getOrderNum());
			self.setSn(mr.getSn());
			self.setParent(mr.getParent());
			self.setPsn(mr.getPsn());
		}

		getSession().saveOrUpdate(self);
	}

	@Override
	public List<MenuResource> listModelMenuByType(String psn, int pos) {
		String hql = getSelectHql()
				+ " from "
				+ MenuResource.class.getSimpleName()
				+ " m where m.psn=? and m.display=1 and m.menuPos=? order by m.orderNum";
		return findListByHql(hql, psn, pos);
	}

	@Override
	public List<MenuResource> listTopMenu() {
		String hql = getSelectHql() + " from "
				+ MenuResource.class.getSimpleName()
				+ " m where m.menuPos=? and m.display=1 order by m.orderNum";
		return findListByHql(hql, MenuPos.NAV_TOP);
	}

	@Override
	public List<LeftMenuDto> listLeftNav() {
		String hql = getSelectHql() + " from "
				+ MenuResource.class.getSimpleName()
				+ " m where m.menuPos=? and m.display=1 order by m.orderNum";
		List<MenuResource> mrs = findListByHql(hql, MenuPos.NAV_LEFT); // 得到左边所有菜单，包括父菜单和子菜单
		// 这里构造菜单树对象
		List<LeftMenuDto> lmrs = new ArrayList<>();
		LeftMenuDto lmd = null;
		// 查找父节点
		for (MenuResource m : mrs) {
			if (m.getParent() != null
					&& m.getParent().getMenuPos() == MenuPos.MENU_ROOT) {
				lmd = new LeftMenuDto();
				lmd.setParent(m);
				lmd.setChildren(new ArrayList<MenuResource>());
				lmrs.add(lmd);
			}
		}

		// 添加子菜单节点
		for (MenuResource m : mrs) {
			if (m.getParent() != null
					&& m.getParent().getMenuPos() == MenuPos.NAV_LEFT) {
				lmd = new LeftMenuDto();
				lmd.setParent(m);
				if(lmrs.contains(lmd)) {
					lmrs.get(lmrs.indexOf(lmd)).getChildren().add(m);
				}
			}
		}

		//排序父菜单
		Collections.sort(lmrs);

		//排序子菜单
		for(LeftMenuDto md : lmrs) {
			Collections.sort(md.getChildren());
		}
		
		return lmrs;
	}

	@Override
	public List<MenuResource> listByParent(Integer pid) {
		String hql = null;
		if(pid == null || pid <= 0) {
			hql = getSelectHql() + " from " + MenuResource.class.getSimpleName() + " m where m.parent.id is null and m.display=1 order by orderNum";
		}else {
			hql = getSelectHql() + " from " + MenuResource.class.getSimpleName() + " m where m.parent.id = ? and m.display=1 order by orderNum";
		}
		
		return findListByHql(hql, pid);
	}
}
