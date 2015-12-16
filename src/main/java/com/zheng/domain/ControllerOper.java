package com.zheng.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * 资源操作符，用于标识每一个操作对应的controller方法
 *
 * @author zhenglian
 * @data 2015年12月16日 下午9:59:33
 */
@Entity
@Table(name="t_controller_oper")
public class ControllerOper {
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 资源标识，默认就使用ADD, DELETE, UPDATE,READ
	 */
	private String sn;
	/**
	 * 资源的方法，一个操作默认会对应多个方法，如add|addInput,
	 * 在初始化的时候，可以根据方法的名称来确定如add就是ADD，其他没有声明
	 * 的都是READ
	 */
	private String methodName;
	private String name;
	/**
	 * 方法的索引位置
	 * 默认情况0-->CREATE, 1-->READ, 2-->UPDATE, 3-->DELETE,其他自定
	 */
	private Integer indexRes;

	/**
	 * 所对应的资源id
	 */
	private Integer rid; //关联controllerResource外键
	private String rsn; //关联controllerResource资源对象sn

	public Long getId() {
		return id;
	}

	public Integer getIndexRes() {
		return indexRes;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getName() {
		return name;
	}

	public Integer getRid() {
		return rid;
	}

	public String getRsn() {
		return rsn;
	}

	public String getSn() {
		return sn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIndexRes(Integer indexRes) {
		this.indexRes = indexRes;
	}

	public void setMethodName(String methodName) {
		if(StringUtils.isBlank(methodName)) {
			this.methodName = methodName;
		}else {
			if(this.methodName.indexOf(methodName) == -1) {
				this.methodName += "|" + methodName;
			}
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public void setRsn(String rsn) {
		this.rsn = rsn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
