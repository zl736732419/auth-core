package com.zheng.auth.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * controller权限资源，管理controller类的操作
 *
 * @author zhenglian
 * @data 2015年12月16日 下午9:51:02
 */
@Entity
@Table(name = "t_controller_resource")
public class ControllerResource implements SystemResource {
	public static final String RES_TYPE = "controller";
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String classname; //classname，默认使用类的权限类名，可能会存在多个值
	private Integer order; // 顺序
	private ControllerResource parent;

	private String sn; // 用于在系统中显示标识，默认使用类简单名

	private String psn; // 父级资源标识

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

	public String getClassname() {
		return classname;
	}
	
	public void setClassname(String classname) {
		if(StringUtils.isBlank(classname)) {
			this.classname = classname;
		}else {
			if(this.classname.indexOf(classname) == -1) {
				this.classname += "|" + classname;
			}
		}
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@ManyToOne
	@JoinColumn(name = "parentId")
	public ControllerResource getParent() {
		return parent;
	}

	public void setParent(ControllerResource parent) {
		this.parent = parent;
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

}
