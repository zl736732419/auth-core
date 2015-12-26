package com.zheng.auth.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_menu_resource")
public class MenuResource implements SystemResource, Comparable<MenuResource> {
	public static final String RES_TYPE = "menu";
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	/**
	 * 为后面生成的a标签生成一个属性auth_sn=auth
	 */
	private String sn;
	private String psn;
	private Integer menuPos;
	private String href;
	private String icon;
	private Integer orderNum;
	private Integer display;
	@ManyToOne
	@JoinColumn(name = "parentId")
	private MenuResource parent;

	public MenuResource() {
		
	}
	
	public MenuResource(Long id, String name, String sn, String psn,
			Integer menuPos, String href, String icon, Integer orderNum,
			Integer display) {
		this.id = id;
		this.name = name;
		this.sn = sn;
		this.psn = psn;
		this.menuPos = menuPos;
		this.href = href;
		this.icon = icon;
		this.orderNum = orderNum;
		this.display = display;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPsn() {
		return psn;
	}

	public void setPsn(String psn) {
		this.psn = psn;
	}

	public Integer getMenuPos() {
		return menuPos;
	}

	public void setMenuPos(Integer menuPos) {
		this.menuPos = menuPos;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public MenuResource getParent() {
		return parent;
	}

	public void setParent(MenuResource parent) {
		this.parent = parent;
	}

	@Override
	public int compareTo(MenuResource o) {
		return (this.orderNum > o.getOrderNum() ? 1 : (this.orderNum == o
				.getOrderNum() ? 0 : -1));
	}

}
