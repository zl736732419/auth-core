package com.zheng.auth.dto;

import java.util.List;

import sun.misc.Compare;

import com.zheng.auth.domain.MenuResource;

/**
 * 左边菜单树实体
 *
 * @author zhenglian
 * @data 2015年12月26日 上午9:03:30
 */
public class LeftMenuDto implements Comparable<LeftMenuDto> {

	private MenuResource parent;
	private List<MenuResource> children;

	public MenuResource getParent() {
		return parent;
	}

	public void setParent(MenuResource parent) {
		this.parent = parent;
	}

	public List<MenuResource> getChildren() {
		return children;
	}

	public void setChildren(List<MenuResource> children) {
		this.children = children;
	}

	@Override
	public boolean equals(Object obj) {
		// 使用list.contains判断是否存在时需要使用到
		LeftMenuDto lmd = (LeftMenuDto) obj;
		return lmd.getParent().getId() == this.getParent().getId();
	}

	@Override
	public int compareTo(LeftMenuDto o) {
		// 实现菜单排序需要使用到
		if (this.parent.getOrderNum() > o.getParent().getOrderNum()) {
			return 1;
		}else if(this.parent.getOrderNum() == o.getParent().getOrderNum()) {
			return 0;
		}else {
			return -1;
		}
	}

}
