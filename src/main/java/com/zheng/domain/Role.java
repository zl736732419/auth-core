package com.zheng.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色实体
 *
 * @author zhenglian
 * @data 2015年12月15日 下午11:12:35
 */
@Entity
@Table(name = "t_role")
public class Role implements Pricipal {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String sn;

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

}
