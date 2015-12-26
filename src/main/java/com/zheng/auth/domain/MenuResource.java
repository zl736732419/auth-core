package com.zheng.auth.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_menu_resource")
public class MenuResource implements SystemResource {
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
	private Integer order;
	private Integer display;
	@ManyToOne
	@JoinColumn(name = "parentId")
	private MenuResource parent;

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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public MenuResource getParent() {
		return parent;
	}

	public void setParent(MenuResource parent) {
		this.parent = parent;
	}

}
