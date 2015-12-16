package com.zheng.domain;

/**
 * 菜单位置枚举
 *
 * @author zhenglian
 * @data 2015年12月16日 下午10:12:09
 */
public enum MenuPos {
	NAV_LEFT("左边导航菜单"), 
	NAV_TOP("顶部菜单"), 
	UN_NAV("非菜单"),
	/**
	 * 每一个模块下表格的工具栏按钮
	 */
	MODEL_NAV("模块导航"), 
	/**
	 * 每一个模块表格记录中的操作按钮
	 */
	MODEL_OPER("模块操作");

	private String name;

	private MenuPos(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
