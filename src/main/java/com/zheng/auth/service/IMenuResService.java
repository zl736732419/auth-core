package com.zheng.auth.service;

import java.util.List;

import com.zheng.auth.domain.MenuResource;
import com.zheng.auth.dto.LeftMenuDto;

public interface IMenuResService extends IBaseService<MenuResource> {
	/**
	 * 添加菜单到父节点
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 上午9:01:29
	 * @param mr
	 * @param psn
	 */
	public void add(MenuResource mr, String psn);

	/**
	 * 根据菜单的位置和父类Menu的sn获取所有的菜单资源对象
	 * 
	 * @param psn
	 * @param pos
	 * @return
	 */
	public List<MenuResource> listModelMenuByType(String psn, int pos);

	/**
	 * 获取顶部的菜单资源对象
	 * 
	 * @return
	 */
	public List<MenuResource> listTopMenu();

	/**
	 * 获取左边导航的菜单资源对象
	 * 
	 * @return
	 */
	public List<LeftMenuDto> listLeftNav();

	/**
	 * 列出父菜单下的所有子菜单
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 上午9:04:16
	 * @param pid
	 * @return
	 */
	public List<MenuResource> listByParent(Integer pid);
	
	/**
	 * 初始化menu菜单
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 下午3:37:12
	 * @param packages
	 */
	public void initMenuResources(String[] packages);
}
